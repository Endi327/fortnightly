package com.align.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = ArticleEntity.Schema.tableName,
    primaryKeys = [ArticleEntity.Schema.id]
)
data class ArticleEntity(
    @ColumnInfo(name = Schema.id) val id: String,
    @ColumnInfo(name = Schema.source) val source: SourceEntity,
    @ColumnInfo(name = Schema.author) val author: String?,
    @ColumnInfo(name = Schema.title) val title: String?,
    @ColumnInfo(name = Schema.description) val description: String?,
    @ColumnInfo(name = Schema.url) val url: String?,
    @ColumnInfo(name = Schema.urlToImage) val urlToImage: String?,
    @ColumnInfo(name = Schema.publishedAt) val publishedAt: String,
    @ColumnInfo(name = Schema.content) val content: String?
) {
    object Schema {
        const val tableName = "articles"
        const val id = "id"
        const val source = "source"
        const val author = "author"
        const val title = "title"
        const val description = "description"
        const val url = "url"
        const val urlToImage = "urlToImage"
        const val publishedAt = "publishedAt"
        const val content = "content"
    }
}