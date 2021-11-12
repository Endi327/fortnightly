package com.align.splash

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.align.core.mvi.BaseFragment
import com.align.core.mvi.CoBinder
import com.align.core.viewbinding.viewBinding
import com.align.splash.databinding.FragmentSplashBinding
import com.google.android.material.transition.MaterialFadeThrough

class SplashFragment: BaseFragment<Nothing, SplashViewModel.State, SplashViewModel.News>(R.layout.fragment_splash) {

    override val mViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun bindStates(binder: CoBinder<SplashViewModel.State>) {
        TODO("Not yet implemented")
    }

    override fun presentNews(news: SplashViewModel.News) {
        when (news) {
            SplashViewModel.News.NoDataNoNetwork -> TODO()
            SplashViewModel.News.NavigateForward -> findNavController().navigate(
                SplashFragmentDirections.splashToList()
            )
        }
    }
}