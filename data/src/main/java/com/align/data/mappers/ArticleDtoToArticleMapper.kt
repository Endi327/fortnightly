package com.align.data.mappers

import com.align.common.toFortnighltyLocalDateTime
import com.align.data.network.entities.ArticleDto
import com.align.domain.entities.Article

internal class ArticleDtoToArticleMapper: Mapper<ArticleDto, Article> {
    override fun map(from: ArticleDto): Article = from.run {
        Article(
            source = SourceDtoToSourceMapper().map(source),
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt?.toFortnighltyLocalDateTime(),
            content = content
        )
    }
}