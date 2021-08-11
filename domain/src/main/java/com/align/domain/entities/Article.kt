package com.align.domain.entities

import java.time.LocalDateTime

data class Article(
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: LocalDateTime?,
    val content: String?
)