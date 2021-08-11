package com.align.core.mvi.feature

import com.align.core.mvi.elements.Bootstrapper
import com.align.core.mvi.elements.Reducer
import kotlinx.coroutines.flow.flowOf

class LoaderFeature<Action : Any, State : Any>(
    initialState: State,
    bootstrapper: Bootstrapper<Action>,
    reducer: Reducer<State, Action>
) : ActorLoaderFeature<Action, Action, State>(
    initialState = initialState,
    actor = { _, action -> flowOf(action) },
    reducer = reducer,
    bootstrapper = bootstrapper
)
