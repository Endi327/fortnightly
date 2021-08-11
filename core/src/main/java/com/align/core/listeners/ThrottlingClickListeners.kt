package com.align.core.listeners

import android.os.SystemClock
import android.view.View
import com.align.core.R

const val CLICKTIMEOUT = 700L

fun View.setThrottlingOnClickListener(action: View.OnClickListener, timeout: Long = CLICKTIMEOUT) {
    this.setOnClickListener {
        val currentTime = SystemClock.elapsedRealtime()

        val lastClickTime = (this.getTag(R.id.view_lastclicktime) as? Long) ?: -1

        if (currentTime - lastClickTime >= timeout || lastClickTime == -1L) {
            this.setTag(R.id.view_lastclicktime, currentTime)
            action.onClick(it)
        }
    }
}

fun View.setThrottlingOnClickListener(action: (v: View) -> Unit, timeout: Long = CLICKTIMEOUT) {
    this.setOnClickListener {
        val currentTime = SystemClock.elapsedRealtime()

        val lastClickTime = (this.getTag(R.id.view_lastclicktime) as? Long) ?: -1

        if (currentTime - lastClickTime >= timeout || lastClickTime == -1L) {
            this.setTag(R.id.view_lastclicktime, currentTime)
            action.invoke(it)
        }
    }
}

fun View.setThrottlingOnClickListener(action: () -> Any?, timeout: Long = CLICKTIMEOUT) {
    this.setOnClickListener {
        val currentTime = SystemClock.elapsedRealtime()

        val lastClickTime = (this.getTag(R.id.view_lastclicktime) as? Long) ?: -1

        if (currentTime - lastClickTime >= timeout || lastClickTime == -1L) {
            this.setTag(R.id.view_lastclicktime, currentTime)
            action.invoke()
        }
    }
}

fun View.setThrottlingOnClickListener(action: () -> Any?) {
    this.setOnClickListener {
        val currentTime = SystemClock.elapsedRealtime()

        val lastClickTime = (this.getTag(R.id.view_lastclicktime) as? Long) ?: -1

        if (currentTime - lastClickTime >= CLICKTIMEOUT || lastClickTime == -1L) {
            this.setTag(R.id.view_lastclicktime, currentTime)
            action.invoke()
        }
    }
}
