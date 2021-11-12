package com.align.domain

import retrofit2.HttpException

sealed class FortnightlyError {
    object HttpError : FortnightlyError()
    object UnknownError : FortnightlyError()
}

fun Throwable.toFortnightlyError(): FortnightlyError = when (this) {
    is HttpException -> FortnightlyError.HttpError
    else -> FortnightlyError.UnknownError
}
