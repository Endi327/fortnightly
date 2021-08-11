package com.align.core.text

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorInt

class SimpleClickSpan(@ColorInt private val linkColor: Int, private val block: (view: View) -> Unit) : ClickableSpan() {
    override fun onClick(widget: View) = block(widget)

    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = true
        ds.color = linkColor
    }
}
