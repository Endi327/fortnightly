package com.align.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.align.data.database.daos.ArticleDao
import com.align.data.database.entities.ArticleEntity
import com.align.data.database.entities.SourceEntity

@Database(
    entities = [
        ArticleEntity::class,
        SourceEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
