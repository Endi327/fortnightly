package com.align.core.mvi.feature

import com.align.core.mvi.elements.Actor
import com.align.core.mvi.elements.Bootstrapper
import com.align.core.mvi.elements.NewsPublisher
import com.align.core.mvi.elements.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

open class ReducerFeature<Wish : Any, State : Any, News : Any>(
    initialState: State,
    reducer: Reducer<State, Wish>,
    bootstrapper: Bootstrapper<Wish>? = null,
    newsPublisher: SimpleNewsPublisher<Wish, State, News>? = null
) : BaseFeature<Wish, Wish, Wish, State, News>(
    initialState = initialState,
    bootstrapper = bootstrapper,
    wishToAction = { wish -> wish },
    actor = BypassActor(),
    reducer = reducer,
    newsPublisher = newsPublisher
) {
    class BypassActor<in State : Any, Wish : Any> : Actor<State, Wish, Wish> {
        override fun invoke(state: State, wish: Wish): Flow<Wish> = flowOf(wish)
    }

    interface SimpleNewsPublisher<in Wish : Any, in State : Any, out News : Any> :
        NewsPublisher<Wish, Wish, State, News> {
        override fun invoke(wish: Wish, effect: Wish, state: State): News? = invoke(wish, state)
        operator fun invoke(wish: Wish, state: State): News?
    }
}
