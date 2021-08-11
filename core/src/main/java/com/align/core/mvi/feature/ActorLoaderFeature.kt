package com.align.core.mvi.feature

import com.align.core.mvi.elements.Actor
import com.align.core.mvi.elements.Bootstrapper
import com.align.core.mvi.elements.PostProcessor
import com.align.core.mvi.elements.Reducer

open class ActorLoaderFeature<Action : Any, Effect : Any, State : Any>(
    initialState: State,
    bootstrapper: Bootstrapper<Action>,
    actor: Actor<State, Action, Effect>,
    reducer: Reducer<State, Effect>,
    postProcessor: PostProcessor<Action, Effect, State>? = null
) : BaseFeature<Nothing, Action, Effect, State, Nothing>(
    initialState = initialState,
    bootstrapper = bootstrapper,
    wishToAction = { wish -> wish },
    actor = actor,
    reducer = reducer,
    postProcessor = postProcessor
)
