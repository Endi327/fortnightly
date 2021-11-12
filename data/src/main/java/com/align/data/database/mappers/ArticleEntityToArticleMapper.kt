package com.align.data.database.mappers

import com.align.common.toFortnighltyLocalDateTime
import com.align.data.database.entities.ArticleEntity
import com.align.data.mappers.Mapper
import com.align.domain.entities.Article

internal class ArticleEntityToArticleMapper: Mapper<ArticleEntity, Article> {
    override fun map(from: ArticleEntity): Article = from.run {
        Article(
            source = SourceEntityToSourceMapper().map(source),
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt.toFortnighltyLocalDateTime(),
            content = content
        )
    }
}