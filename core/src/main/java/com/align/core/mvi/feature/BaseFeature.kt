package com.align.core.mvi.feature

import com.align.core.mvi.elements.Actor
import com.align.core.mvi.elements.Bootstrapper
import com.align.core.mvi.elements.NewsPublisher
import com.align.core.mvi.elements.PostProcessor
import com.align.core.mvi.elements.Reducer
import com.align.core.mvi.elements.WishToAction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import timber.log.Timber

open class BaseFeature<Wish : Any, in Action : Any, in Effect : Any, out State : Any, News : Any>(
    initialState: State,
    private val bootstrapper: Bootstrapper<Action>? = null,
    private val wishToAction: WishToAction<Wish, Action>,
    actor: Actor<State, Action, Effect>,
    reducer: Reducer<State, Effect>,
    postProcessor: PostProcessor<Action, Effect, State>? = null,
    newsPublisher: NewsPublisher<Action, Effect, State, News>? = null,
    private val tag: String = "ViewModel"
) : Feature<Wish, State, News> {
    private val actionChannel: Channel<Action> = Channel(Channel.BUFFERED)
    private val stateFlow: MutableStateFlow<State> = MutableStateFlow(initialState)
    private val newsFlow: MutableSharedFlow<News> = MutableSharedFlow()
    private val mutex = Mutex()
    private var isStarted: Boolean = false

    private val postProcessorWrapper: PostProcessorWrapper<Action, Effect, State>? =
        postProcessor?.let {
            PostProcessorWrapper(
                postProcessor,
                actionChannel,
                tag
            )
        }

    private val newsPublisherWrapper: NewsPublisherWrapper<Action, Effect, State, News>? =
        newsPublisher?.let {
            NewsPublisherWrapper(
                newsPublisher,
                newsFlow,
                tag
            )
        }

    private val reducerWrapper: ReducerWrapper<State, Action, Effect, News> = ReducerWrapper(
        reducer = reducer,
        states = stateFlow,
        postProcessorWrapper = postProcessorWrapper,
        newsPublisherWrapper = newsPublisherWrapper,
        tag = tag
    )

    private val actorWrapper: ActorWrapper<State, Action, Effect, News> = ActorWrapper(
        actor = actor,
        stateSubject = stateFlow,
        reducerWrapper = reducerWrapper,
        mutex = mutex,
        tag = tag
    )

    override val state: StateFlow<State>
        get() = stateFlow

    override val news: Flow<News>
        get() = newsFlow

    @ExperimentalCoroutinesApi
    @Suppress("TooGenericExceptionCaught")
    override suspend fun sendWish(wish: Wish) {
        Timber.tag(tag).v("Wish received: $wish")

        if (!actionChannel.isClosedForSend) {
            val action = wishToAction(wish)
            actionChannel.send(action)
        }
    }

    override suspend fun start() {
        if (!isStarted) {
            isStarted = true
            Timber.tag(tag).d("Start")

            coroutineScope {
                actionChannel.consumeAsFlow().onStart {
                    bootstrapper?.invoke()?.collect { action ->
                        Timber.tag(tag).v("Bootstrapper action received: $action")
                        emit(action)
                    }
                }.collect { action ->
                    Timber.tag(tag).v("Action received: $action")
                    launch { actorWrapper.emit(action) }
                }
            }
        } else {
            Timber.tag(tag).d("Already started")
        }
    }

    private class ActorWrapper<State : Any, Action : Any, Effect : Any, News : Any>(
        private val actor: Actor<State, Action, Effect>,
        private val stateSubject: StateFlow<State>,
        private val reducerWrapper: ReducerWrapper<State, Action, Effect, News>,
        private val mutex: Mutex,
        private val tag: String
    ) : FlowCollector<Action> {
        override suspend fun emit(value: Action) {
            Timber.tag(tag).v("Process action: $value")

            val originalState = stateSubject.value

            actor(originalState, value).collect { change ->
                mutex.withLock {
                    val currentState = stateSubject.value
                    reducerWrapper.processChange(currentState, value, change)
                }
            }
        }
    }

    private class ReducerWrapper<State : Any, Action : Any, Effect : Any, News : Any>(
        private val reducer: Reducer<State, Effect>,
        private val states: FlowCollector<State>,
        private var postProcessorWrapper: PostProcessorWrapper<Action, Effect, State>?,
        private val newsPublisherWrapper: NewsPublisherWrapper<Action, Effect, State, News>?,
        private val tag: String
    ) {
        suspend fun processChange(state: State, action: Action, effect: Effect) {
            val newState = reducer(state, effect)
            Timber.tag(tag).v("Reducer: New state available.")

            states.emit(newState)

            postProcessorWrapper?.postProcess(action, effect, newState)
            newsPublisherWrapper?.publishNews(action, effect, newState)
        }
    }

    private class NewsPublisherWrapper<Action : Any, Effect : Any, State : Any, News : Any>(
        private val newsPublisher: NewsPublisher<Action, Effect, State, News>,
        private val news: FlowCollector<News>,
        private val tag: String
    ) {
        suspend fun publishNews(action: Action, effect: Effect, state: State) {
            newsPublisher(action, effect, state)?.let {
                Timber.tag(tag).v("Send news: $it")
                news.emit(it)
            }
        }
    }

    private class PostProcessorWrapper<Action : Any, Effect : Any, State : Any>(
        private val postProcessor: PostProcessor<Action, Effect, State>,
        private val actions: SendChannel<Action>,
        private val tag: String
    ) {
        suspend fun postProcess(action: Action, effect: Effect, state: State) {
            postProcessor(action, effect, state)?.let {
                Timber.tag(tag).v("Send post processed action: $it")
                actions.send(it)
            }
        }
    }
}
