package com.align.core.threads

import timber.log.Timber

fun logThrread(message: String) {
    Timber.i("\"$message; thread name: ${Thread.currentThread().name}; thread ID: ${Thread.currentThread().id}\"")
}