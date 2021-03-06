package com.align.data.usecase

import com.align.data.mappers.ArticleDtoToArticleMapper
import com.align.data.mappers.mapNotNullList
import com.align.data.network.FortnightlyService
import com.align.domain.ConstantsProvider
import com.align.domain.entities.Article
import com.align.domain.usecases.GetArticlesUseCase
import com.align.domain.usecases.common.UseCase
import com.align.domain.usecases.parameters.GetArticlesParams
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DefaultGetArticlesUseCase @Inject constructor(
    private val fortnightlyService: FortnightlyService,
    private val constantsProvider: ConstantsProvider,
    coroutineDispatcher: CoroutineDispatcher
): UseCase<GetArticlesParams, Pair<List<Article>, Int>>(coroutineDispatcher), GetArticlesUseCase {
    override suspend fun execute(parameters: GetArticlesParams): Pair<List<Article>, Int> {
        val response = fortnightlyService.getArticles(
            apiKey = constantsProvider.apiKey,
            page = parameters.page,
            pageSize = parameters.size
        )

        val articles = ArticleDtoToArticleMapper().mapNotNullList(response.articles)
        val totalCount = response.totalResults

        return Pair(articles, totalCount)
    }
}