package com.align.core.text

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

fun SpannableStringBuilder.appendText(
    context: Context,
    @StringRes text: Int,
    @FontRes font: Int? = null,
    @DimenRes size: Int? = null,
    @ColorRes color: Int? = null
) {
    appendText(context, context.getString(text), font, size, color)
}

fun SpannableStringBuilder.appendText(
    context: Context,
    text: String,
    @FontRes font: Int? = null,
    @DimenRes size: Int? = null,
    @ColorRes color: Int? = null
) {
    val textLength = text.length

    val spannable = SpannableString(text)

    spannable.apply {
        font?.let {
            setFont(context, it)
        }

        size?.let {
            setSpan(AbsoluteSizeSpan(context.resources.getDimension(it).toInt()), 0,
                textLength, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        }

        color?.let {
            setColor(context, it)
        }
    }

    append(spannable)
}

fun SpannableStringBuilder.appendTextColorInt(
    context: Context,
    text: String,
    @FontRes font: Int? = null,
    @DimenRes size: Int? = null,
    @ColorInt color: Int? = null
) {
    val textLength = text.length

    val spannable = SpannableString(text)

    spannable.apply {
        font?.let {
            setFont(context, it)
        }

        size?.let {
            setSpan(
                AbsoluteSizeSpan(context.resources.getDimension(it).toInt()), 0,
                textLength, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        }

        color?.let {
            setColorInt(it)
        }
    }

    append(spannable)
}

fun SpannableStringBuilder.appendClickableText(
    context: Context,
    @StringRes text: Int,
    @FontRes font: Int? = null,
    @DimenRes size: Int? = null,
    @ColorRes color: Int? = null,
    @ColorInt linkColor: Int,
    onClick: (View) -> Unit
) {
    appendClickableText(context, context.getString(text), font, size, color, linkColor, onClick)
}

fun SpannableStringBuilder.appendClickableText(
    context: Context,
    text: String,
    @FontRes font: Int? = null,
    @DimenRes size: Int? = null,
    @ColorRes color: Int? = null,
    @ColorInt linkColor: Int,
    onClick: (View) -> Unit
) {
    appendText(context, text, font, size, color)

    setSpan(
        SimpleClickSpan(linkColor, onClick),
        this.length - text.length,
        this.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

fun Spannable.setFont(context: Context, @FontRes font: Int) {
    val typeface = ResourcesCompat.getFont(context, font)

    setSpan(TypefaceSpanCompat(typeface!!), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun Spannable.setColor(context: Context, @ColorRes textColor: Int) {
    ForegroundColorSpan(ContextCompat.getColor(context, textColor)).also {
        setSpan(it, 0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}

fun Spannable.setColorInt(@ColorInt textColor: Int) {
    ForegroundColorSpan(textColor).also {
        setSpan(it, 0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}