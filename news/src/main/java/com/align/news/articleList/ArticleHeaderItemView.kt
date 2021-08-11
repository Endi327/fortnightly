package com.align.news.articleList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.align.common.toMadeAtString
import com.align.domain.entities.Article
import com.align.news.R
import com.align.news.databinding.HeaderArticleBinding
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ArticleHeaderItemView(
    val article: Article
): AbstractBindingItem<HeaderArticleBinding>() {

    override val type: Int = R.id.article_header_id

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): HeaderArticleBinding =
        HeaderArticleBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HeaderArticleBinding, payloads: List<Any>) {
        binding.title.text = article.title
        binding.hours.text = article.publishedAt?.toMadeAtString()
        Glide.with(binding.root.context).load(article.urlToImage).into(binding.image)
    }
}