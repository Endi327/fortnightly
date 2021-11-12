package com.align.splash

import com.align.core.mvi.BaseViewModel
import com.align.core.mvi.feature.ActorReducerNewsFeature
import com.align.domain.FortnightlyError
import com.align.domain.connectivity.ConnectivityProvider
import com.align.domain.usecases.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    getArticlesUseCase: GetArticlesUseCase,
    private val connectivityProvider: ConnectivityProvider
): BaseViewModel<Nothing, SplashViewModel.State, SplashViewModel.News>() {

    sealed class Action {
        object GetNews: Action()
    }

    data class State(
        val loading: Boolean
    )

    sealed class Effect {
        object LoadNewsSuccess: Effect()
        data class LoadNewsFailed(val error: FortnightlyError): Effect()

        object NoDataNoNetwork: Effect()
    }

    sealed class News {
        object NoDataNoNetwork: News()
        object NavigateForward: News()
    }

    override val feature: ActorReducerNewsFeature<Action, Effect, State, News> =
        ActorReducerNewsFeature(
            initialState = State(loading = true),
            bootstrapper = { flowOf(Action.GetNews) },
            actor = { _, action ->
                when (action) {
                    Action.GetNews -> flow {
                        getArticlesUseCase(null).foldResult(
                            onSuccess = { (articles, _) ->
                                if (articles.isEmpty() && !connectivityProvider.getNetworkState().hasInternet) {
                                    emit(Effect.NoDataNoNetwork)
                                } else {
                                    emit(Effect.LoadNewsSuccess)
                                }
                            },
                            onFailure = {
                                emit(Effect.LoadNewsFailed(it))
                            }
                        )
                    }
                }
            },
            reducer = { state, effect ->
                when (effect) {
                    is Effect.LoadNewsFailed -> state.copy(loading = false)
                    Effect.LoadNewsSuccess -> state.copy(loading = false)
                    Effect.NoDataNoNetwork -> state.copy(loading = false)
                }
            },
            newsPublisher = { _, effect, _ ->
                when (effect) {
                    is Effect.LoadNewsFailed -> News.NavigateForward
                    Effect.LoadNewsSuccess -> News.NavigateForward
                    Effect.NoDataNoNetwork -> News.NoDataNoNetwork
                }
            }
        )

}