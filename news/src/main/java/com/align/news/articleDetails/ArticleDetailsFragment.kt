package com.align.news.articleDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.align.core.viewbinding.viewBinding
import com.align.news.R
import com.align.news.databinding.FragmentArticleDetailsBinding
import com.bumptech.glide.Glide

internal class ArticleDetailsFragment: Fragment(R.layout.fragment_article_details) {

    private val binding: FragmentArticleDetailsBinding by viewBinding(FragmentArticleDetailsBinding::bind)

    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = args.title
        binding.category.text = args.category
        binding.description.text = args.description
        Glide.with(binding.root.context).load(args.image).into(binding.image)
    }
}