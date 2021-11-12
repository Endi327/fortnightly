package com.align.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = SourceEntity.Schema.tableName,
    primaryKeys = [SourceEntity.Schema.id]
)
data class SourceEntity(
    @ColumnInfo(name = Schema.id) val id: String,
    @ColumnInfo(name = Schema.name) val name: String,
) {
    object Schema {
        const val tableName = "sources"
        const val id = "id"
        const val name = "name"
    }
}
