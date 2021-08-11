package com.align.core.mvi

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseActivity<Wish : Any, State : Any, News : Any> : AppCompatActivity() {
    protected abstract val mViewModel: BaseViewModel<Wish, State, News>

    private val binder: CoBinder<State> = CoBinder()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindStates(binder)

        subscribeToViewModel()
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
    override fun onDestroy() {
        binder.clear()
        super.onDestroy()
    }

    abstract fun bindStates(binder: CoBinder<State>)

    abstract fun presentNews(news: News)

    protected fun sendWish(wish: Wish) {
        mViewModel.sendWish(wish)
    }

    private var stateJob: Job? = null
    private var newsJob: Job? = null

    private fun subscribeToViewModel() {
        if (stateJob == null || newsJob == null) {
            stateJob = lifecycleScope.launchWhenCreated {
                mViewModel.state.collect {
                    binder.newState(it)
                }
            }

            newsJob = lifecycleScope.launch {
                mViewModel.news.collect { presentNews(it) }
            }

            mViewModel.onStart()
        }
    }
}
