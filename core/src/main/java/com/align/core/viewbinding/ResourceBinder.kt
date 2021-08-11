package com.align.core.viewbinding

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Activity.size(@DimenRes id: Int) = lazy { resources.getDimension(id) }

fun Activity.integer(@IntegerRes id: Int) = lazy { resources.getInteger(id) }

fun Activity.string(@StringRes id: Int) = lazy { resources.getString(id) }

fun Activity.color(@ColorRes id: Int) = lazy { ContextCompat.getColor(this, id) }

fun Activity.drawable(@DrawableRes id: Int) = lazy { ContextCompat.getDrawable(this, id) }

fun Activity.animation(@AnimRes id: Int) = lazy { AnimationUtils.loadAnimation(this, id) }


fun Fragment.size(@DimenRes id: Int) = lazy { resources.getDimension(id) }

fun Fragment.integer(@IntegerRes id: Int) = lazy { resources.getInteger(id) }

fun Fragment.string(@StringRes id: Int) = lazy { resources.getString(id) }

fun Fragment.color(@ColorRes id: Int) = lazy { ContextCompat.getColor(requireContext(), id) }

fun Fragment.drawable(@DrawableRes id: Int) = lazy { ContextCompat.getDrawable(requireContext(), id) }

fun Fragment.animation(@AnimRes id: Int) = lazy { AnimationUtils.loadAnimation(requireContext(), id) }


fun <V : View> V.size(@DimenRes dimenRes: Int): Lazy<Int> = lazy { resources.getDimensionPixelSize(dimenRes) }

fun <V : View> V.integer(@IntegerRes integerRes: Int): Lazy<Int> = lazy { resources.getInteger(integerRes) }

fun <V : View> V.string(@StringRes stringRes: Int): Lazy<String> = lazy { resources.getString(stringRes) }

fun <V : View> V.color(@ColorRes colorRes: Int): Lazy<Int> = lazy { ContextCompat.getColor(context, colorRes) }

fun <V : View> V.drawable(@DrawableRes drawableRes: Int): Lazy<Drawable> = lazy {
    context.getDrawable(drawableRes) ?: throw IllegalStateException()
}

fun <V : View> V.animation(@AnimRes id: Int) = lazy { AnimationUtils.loadAnimation(context, id) }