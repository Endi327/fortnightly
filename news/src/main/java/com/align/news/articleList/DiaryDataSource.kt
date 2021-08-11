package com.align.news.articleList

import androidx.paging.PageKeyedDataSource
import com.align.domain.entities.Article
import com.align.domain.usecases.GetArticlesUseCase
import com.align.domain.usecases.parameters.GetArticlesParams
import javax.inject.Inject
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class DiaryDataSource @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : PageKeyedDataSource<Int, Article>() {

    private var itemsCount = 0
    private var totalCount = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        runBlocking {
            getArticlesUseCase(
                GetArticlesParams(
                    page = 0,
                    size = params.requestedLoadSize
                )
            ).foldResult(
                onSuccess = { (list, total) ->
                    itemsCount += list.size
                    totalCount = total
                    callback.onResult(list, null, 1)
                },
                onFailure = {
                    Timber.e("Failed to load list")
                }
            )
        }
    }

    @Suppress("EmptyFunctionBlock")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) { }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        val nextPage = params.key

        if (itemsCount == totalCount) {
            callback.onResult(emptyList(), nextPage)
        } else {
            runBlocking {
                getArticlesUseCase.invoke(
                    GetArticlesParams(
                        page = nextPage,
                        size = params.requestedLoadSize
                    )
                ).foldResult(
                    onSuccess = { (list, _) ->
                        itemsCount += list.size
                        callback.onResult(list, nextPage + 1)
                    },
                    onFailure = {
                        Timber.e("Failed to load list")
                    }
                )
            }
        }
    }
}
