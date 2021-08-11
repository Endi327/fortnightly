package com.align.data.network.entities

import com.google.gson.annotations.SerializedName

data class ArticleResponseDto(
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleDto>,
)
