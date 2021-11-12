package com.align.data.database.mappers

import com.align.common.DateFormatters
import com.align.data.database.entities.ArticleEntity
import com.align.data.mappers.Mapper
import com.align.domain.entities.Article

internal class ArticleToArticleEntityMapper: Mapper<Article, ArticleEntity> {
    override fun map(from: Article): ArticleEntity = from.run {
        ArticleEntity(
            id = id,
            source = SourceToSourceEntityMapper().map(source),
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt.format(DateFormatters.isoFormatter),
            content = content
        )
    }
}