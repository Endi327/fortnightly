package com.align.data.database.mappers

import com.align.data.database.entities.SourceEntity
import com.align.data.mappers.Mapper
import com.align.domain.entities.Source

internal class SourceEntityToSourceMapper: Mapper<SourceEntity, Source> {
    override fun map(from: SourceEntity): Source = from.run {
        Source(
            id = id,
            name = name
        )
    }
}