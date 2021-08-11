package com.align.common

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

fun ZonedDateTime.toFortnightlyLocalDateTime(): LocalDateTime {
    return this.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
}

fun String.toFortnighltyLocalDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, DateFormatters.isoFormatter).atZone(ZoneOffset.UTC).toFortnightlyLocalDateTime()
}

fun LocalDateTime.toMadeAtString(): String {
    val duration = Duration.between(this, LocalDateTime.now())
    return if (duration.toHours() < 1) {
        "1H"
    } else {
        if (duration.toHours() > 24) {
            duration.toDays().toString() + "D"
        } else {
            duration.toHours().toString() + "H"
        }
    }
}