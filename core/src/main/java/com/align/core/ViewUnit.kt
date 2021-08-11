package com.align.core

import android.content.res.Resources

fun Int.toIntDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toIntPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toDp(): Float = this / Resources.getSystem().displayMetrics.density

fun Int.toPx(): Float = this * Resources.getSystem().displayMetrics.density

val Int.dp: Int
    get() = this

val Int.px: Int
    get() = this