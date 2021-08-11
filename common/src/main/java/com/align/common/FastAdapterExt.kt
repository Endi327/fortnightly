package com.align.common

import android.os.SystemClock
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.listeners.ClickEventHook

const val CLICKTIMEOUT = 700L

inline fun <reified Binding : ViewBinding, reified Item : GenericItem> FastAdapter<Item>.addThrottlingClickListener(
    crossinline resolveView: (Binding) -> View? = { null },
    crossinline resolveViews: ((Binding) -> List<View>?) = { null },
    crossinline onClick: (v: View, position: Int, fastAdapter: FastAdapter<Item>, item: Item) -> Unit
) {
    addEventHook(object : ClickEventHook<Item>() {
        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? =
            if (viewHolder is BindingViewHolder<*> && viewHolder.binding is Binding) {
                resolveView.invoke(viewHolder.binding as Binding)
            } else {
                super.onBind(viewHolder)
            }

        override fun onBindMany(viewHolder: RecyclerView.ViewHolder): List<View>? =
            if (viewHolder is BindingViewHolder<*> && viewHolder.binding is Binding) {
                resolveViews.invoke(viewHolder.binding as Binding)
            } else {
                super.onBindMany(viewHolder)
            }

        override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<Item>, item: Item) {
            val currentTime = SystemClock.elapsedRealtime()

            val lastClickTime = (v.getTag(R.id.view_lastclicktime) as? Long) ?: -1

            if (currentTime - lastClickTime >= CLICKTIMEOUT || lastClickTime == -1L) {
                v.setTag(R.id.view_lastclicktime, currentTime)
                onClick.invoke(v, position, fastAdapter, item)
            }
        }
    })
}