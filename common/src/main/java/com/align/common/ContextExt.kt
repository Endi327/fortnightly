package com.align.common

import android.content.Context
import android.widget.Toast

fun Context.showToastMessage(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}