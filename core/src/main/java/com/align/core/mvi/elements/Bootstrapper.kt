package com.align.core.mvi.elements

import kotlinx.coroutines.flow.Flow

typealias Bootstrapper<Action> = () -> Flow<Action>
