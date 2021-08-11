package com.align.data.mappers

import com.align.data.network.entities.SourceDto
import com.align.domain.entities.Source

internal class SourceDtoToSourceMapper: Mapper<SourceDto, Source> {
    override fun map(from: SourceDto): Source = from.run {
        Source(
            id = id,
            name = name
        )
    }
}