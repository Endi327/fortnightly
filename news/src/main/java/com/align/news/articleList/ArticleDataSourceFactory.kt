package com.align.news.articleList

import androidx.paging.DataSource
import com.align.domain.entities.Article
import com.align.domain.usecases.GetArticlesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ArticleDataSourceFactory @Inject constructor() : DataSource.Factory<Int, Article>() {

    @Inject
    lateinit var getArticleUseCase: GetArticlesUseCase

    override fun create(): DataSource<Int, Article> {
        return DiaryDataSource(getArticleUseCase)
    }
}
