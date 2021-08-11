package com.align.core.mvi

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.align.core.mvi.feature.Feature
import com.align.core.threads.BackgroundDispatcher.Background
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Wish : Any, State : Any, News : Any> : ViewModel() {
    @VisibleForTesting
    internal open val featureScope: CoroutineScope
        get() = viewModelScope

    @VisibleForTesting
    protected abstract val feature: Feature<Wish, State, News>

    val state: StateFlow<State>
        get() = feature.state

    val news: Flow<News>
        get() = feature.news

    fun onStart() {
        featureScope.launch(Dispatchers.Background) {
            feature.start()
        }
    }

    fun sendWish(wish: Wish) {
        featureScope.launch(Dispatchers.Background) {
            feature.sendWish(wish)
        }
    }
}
