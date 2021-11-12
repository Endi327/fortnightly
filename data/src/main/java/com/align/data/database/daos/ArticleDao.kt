package com.align.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.align.data.database.entities.ArticleEntity

@Dao
interface ArticleDao {

    @Query("SELECT * FROM ${ArticleEntity.Schema.tableName}")
    fun getArticles(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticles(articles: List<ArticleEntity>)
}