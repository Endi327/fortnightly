package com.align.core.mvi.elements

import kotlinx.coroutines.flow.StateFlow

interface Store<Wish : Any, out State : Any> {
    val state: StateFlow<State>

    suspend fun sendWish(wish: Wish)
}
