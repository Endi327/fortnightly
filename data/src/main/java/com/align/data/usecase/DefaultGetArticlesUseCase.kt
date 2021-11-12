package com.align.data.usecase

import com.align.data.database.AppDatabase
import com.align.data.database.mappers.ArticleEntityToArticleMapper
import com.align.data.database.mappers.ArticleToArticleEntityMapper
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
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DefaultGetArticlesUseCase @Inject constructor(
    private val fortnightlyService: FortnightlyService,
    private val constantsProvider: ConstantsProvider,
    private val appDatabase: AppDatabase,
    coroutineDispatcher: CoroutineDispatcher
): UseCase<GetArticlesParams?, Pair<List<Article>, Int>>(coroutineDispatcher), GetArticlesUseCase {
    override suspend fun execute(parameters: GetArticlesParams?): Pair<List<Article>, Int> {
        return try {
            val response = fortnightlyService.getArticles(
                apiKey = constantsProvider.apiKey,
                page = parameters!!.page,
                pageSize = parameters.size
            )

            val articles = ArticleDtoToArticleMapper().mapNotNullList(response.articles)

            appDatabase.articleDao().saveArticles(
                ArticleToArticleEntityMapper().mapNotNullList(articles)
            )

            val totalCount = response.totalResults

            Pair(articles, totalCount)
        } catch (e: UnknownHostException) {
            val articlesDb = ArticleEntityToArticleMapper().mapNotNullList(appDatabase.articleDao().getArticles())
            Pair(articlesDb, articlesDb.size)
        }
    }
}