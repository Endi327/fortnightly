package com.align.common

import java.time.format.DateTimeFormatter

object DateFormatters {
    val isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
}
