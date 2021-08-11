package com.align.data.network

import com.align.data.network.entities.ArticleResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FortnightlyService {

    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("country") country: String = "us"
    ): ArticleResponseDto
}