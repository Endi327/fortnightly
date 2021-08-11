package com.align.core.mvi.elements

import kotlinx.coroutines.flow.Flow

typealias Actor<State, Action, Effect> = (state: State, action: Action) -> Flow<Effect>
