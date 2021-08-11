package com.align.core.viewbinding

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

operator fun <T : ViewBinding> T.invoke(block: T.() -> Unit) = this.run(block)

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T, destroyTask: ((T) -> Unit)? = null) =
    FragmentViewBindingDelegate(this, viewBindingFactory, destroyTask)

val ViewBinding.context: Context
    get() = root.context

val ViewBinding.resources: Resources
    get() = root.resources

fun ViewBinding.getString(@StringRes res: Int): String = context.getString(res)

@Suppress("SpreadOperator")
fun ViewBinding.getString(@StringRes res: Int, vararg args: Any?): String = context.getString(res, *args)