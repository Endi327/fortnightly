package com.align.news.articleList

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.align.core.mvi.BaseViewModel
import com.align.core.mvi.feature.ActorReducerNewsFeature
import com.align.domain.FortnightlyError
import com.align.domain.entities.Article
import com.align.domain.toFortnightlyError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
internal class ArticleListViewModel @Inject constructor(
    articleDataSourceFactory: ArticleDataSourceFactory
): BaseViewModel<Nothing, ArticleListViewModel.State, ArticleListViewModel.News>() {

    companion object {
        private const val defaultPageSize = 50
    }

    @Suppress("MagicNumber")
    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(defaultPageSize)
        .setPageSize(defaultPageSize)
        .build()

    private var articleLiveDataList = LivePagedListBuilder(
        articleDataSourceFactory,
        config
    ).build()

    sealed class Action {
        object GetArticles: Action()
    }

    data class State(
        val loading: Boolean = true,
        val articles: PagedList<Article>? = null
    )

    sealed class Effect {
        data class LoadArticles(val articles: PagedList<Article>): Effect()
        data class ArticleLoadFailed(val error: FortnightlyError): Effect()
    }

    sealed class News {
        data class ShowError(val error: FortnightlyError): News()
    }

    @ExperimentalCoroutinesApi
    override val feature: ActorReducerNewsFeature<Action, Effect, State, News> =
        ActorReducerNewsFeature(
            initialState = State(loading = true),
            bootstrapper = { flowOf(Action.GetArticles) },
            actor = { _, action ->
                when (action) {
                    Action.GetArticles -> articleLiveDataList.asFlow()
                        .onStart {
                            if (articleLiveDataList.value?.isEmpty() == true) {
                                articleLiveDataList.value?.dataSource?.invalidate()
                            }
                        }
                        .map { Effect.LoadArticles(articles = it) }
                        .catch { Effect.ArticleLoadFailed(error = it.toFortnightlyError()) }
                }
            },
            reducer = { state, effect ->
                when (effect) {
                    is Effect.LoadArticles -> state.copy(
                        loading = false,
                        articles = effect.articles
                    )
                    is Effect.ArticleLoadFailed -> state.copy(loading = false)
                }
            },
            newsPublisher = { _, effect, _ ->
                when (effect) {
                    is Effect.ArticleLoadFailed -> News.ShowError(error = effect.error)
                    else -> null
                }
            }
        )

    @ExperimentalCoroutinesApi
    private fun <T> LiveData<T>.asFlow(): Flow<T> = callbackFlow {
        val observer = Observer<T> { value -> offer(value) }
        observeForever(observer)
        awaitClose {
            removeObserver(observer)
        }
    }.flowOn(Dispatchers.Main.immediate)

}