package com.align.core.mvi.elements

typealias Reducer<State, Effect> = (state: State, effect: Effect) -> State
