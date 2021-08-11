package com.align.core.mvi.elements

typealias NewsPublisher<Action, Effect, State, News> = (action: Action, effect: Effect, state: State) -> News?
