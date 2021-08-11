package com.align.core.utils

import android.content.Context
import androidx.core.content.edit
import java.util.UUID

class DeviceIdentifier(private val context: Context) {
    private val preferences by lazy {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun get(): String = if (preferences.contains(DEVICE_ID_KEY)) {
        preferences.getString(DEVICE_ID_KEY, null) ?: throw IllegalStateException("")
    } else {
        val generatedId = UUID.randomUUID().toString()
        preferences.edit { putString(DEVICE_ID_KEY, generatedId) }
        generatedId
    }

    companion object {
        private const val PREFERENCES_NAME = "DeviceID"
        private const val DEVICE_ID_KEY = "did"
    }
}