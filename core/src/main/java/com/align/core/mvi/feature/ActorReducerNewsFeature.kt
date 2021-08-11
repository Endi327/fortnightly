package com.align.core.mvi.feature

import com.align.core.mvi.elements.Actor
import com.align.core.mvi.elements.Bootstrapper
import com.align.core.mvi.elements.NewsPublisher
import com.align.core.mvi.elements.Reducer

open class ActorReducerNewsFeature<Action : Any, in Effect : Any, State : Any, News : Any>(
    initialState: State,
    bootstrapper: Bootstrapper<Action>? = null,
    actor: Actor<State, Action, Effect>,
    reducer: Reducer<State, Effect>,
    newsPublisher: NewsPublisher<Action, Effect, State, News>? = null
) : BaseFeature<Nothing, Action, Effect, State, News>(
    initialState = initialState,
    bootstrapper = bootstrapper,
    wishToAction = { wish -> wish },
    actor = actor,
    reducer = reducer,
    newsPublisher = newsPublisher
)
