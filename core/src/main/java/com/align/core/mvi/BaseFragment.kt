package com.align.core.mvi

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

abstract class BaseFragment<Wish : Any, State : Any, News : Any>(contentLayoutId: Int) : Fragment(contentLayoutId) {
    protected abstract val mViewModel: BaseViewModel<Wish, State, News>

    private val binder: CoBinder<State> = CoBinder()

    private lateinit var resumeObserver: ResumeObserver

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resumeObserver = ResumeObserver(WeakReference(this))
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindStates(binder)

        subscribeToViewModel()

        viewLifecycleOwner.lifecycle.addObserver(resumeObserver)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()

        subscribeToViewModel()
    }

    @CallSuper
    override fun onStop() {
        newsJob?.cancel()
        newsJob = null

        stateJob?.cancel()
        stateJob = null

        super.onStop()
    }

    @CallSuper
    override fun onDestroyView() {
        binder.clear()
        viewLifecycleOwner.lifecycle.removeObserver(resumeObserver)
        super.onDestroyView()
    }

    abstract fun bindStates(binder: CoBinder<State>)

    abstract fun presentNews(news: News)

    @CallSuper
    protected open fun onPostResume() = Unit

    protected fun sendWish(wish: Wish) {
        mViewModel.sendWish(wish)
    }

    private var stateJob: Job? = null
    private var newsJob: Job? = null

    private fun subscribeToViewModel() {
        if (stateJob == null || newsJob == null) {
            stateJob = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                mViewModel.state.collect {
                    binder.newState(it)
                }
            }

            newsJob = viewLifecycleOwner.lifecycleScope.launch {
                mViewModel.news.collect {
                    presentNews(it)
                }
            }

            mViewModel.onStart()
        }
    }

    private class ResumeObserver(private val weakFragment: WeakReference<BaseFragment<*, *, *>>) : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun postResume() {
            weakFragment.get()?.onPostResume()
        }
    }
}