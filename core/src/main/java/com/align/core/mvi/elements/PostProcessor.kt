package com.align.core.mvi.elements

typealias PostProcessor<Action, Effect, State> = (action: Action, effect: Effect, state: State) -> Action?
