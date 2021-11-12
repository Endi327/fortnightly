package com.align.data.database.mappers

import com.align.data.database.entities.SourceEntity
import com.align.data.mappers.Mapper
import com.align.domain.entities.Source
import java.util.UUID

internal class SourceToSourceEntityMapper: Mapper<Source, SourceEntity> {
    override fun map(from: Source): SourceEntity = from.run {
        SourceEntity(
            id = id ?: UUID.randomUUID().toString(),
            name = name
        )
    }
}