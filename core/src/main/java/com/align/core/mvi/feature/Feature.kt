package com.align.core.mvi.feature

import com.align.core.mvi.elements.Store
import kotlinx.coroutines.flow.Flow

interface Feature<Wish : Any, out State : Any, News : Any> : Store<Wish, State> {
    val news: Flow<News>

    suspend fun start()
}
