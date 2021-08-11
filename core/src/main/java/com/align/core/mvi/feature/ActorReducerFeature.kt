package com.align.core.mvi.feature

import com.align.core.mvi.elements.Actor
import com.align.core.mvi.elements.Bootstrapper
import com.align.core.mvi.elements.NewsPublisher
import com.align.core.mvi.elements.Reducer

open class ActorReducerFeature<Wish : Any, in Effect : Any, State : Any, News : Any>(
    initialState: State,
    bootstrapper: Bootstrapper<Wish>? = null,
    actor: Actor<State, Wish, Effect>,
    reducer: Reducer<State, Effect>,
    newsPublisher: NewsPublisher<Wish, Effect, State, News>? = null
) : BaseFeature<Wish, Wish, Effect, State, News>(
    initialState = initialState,
    bootstrapper = bootstrapper,
    wishToAction = { wish -> wish },
    actor = actor,
    reducer = reducer,
    newsPublisher = newsPublisher
)
