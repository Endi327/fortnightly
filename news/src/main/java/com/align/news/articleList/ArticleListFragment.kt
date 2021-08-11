package com.align.news.articleList

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.align.common.addThrottlingClickListener
import com.align.core.mvi.BaseFragment
import com.align.core.mvi.CoBinder
import com.align.core.showError
import com.align.core.viewbinding.viewBinding
import com.align.domain.entities.Article
import com.align.news.R
import com.align.news.databinding.FragmentArticleListBinding
import com.align.news.databinding.HeaderArticleBinding
import com.align.news.databinding.ItemArticleBinding
import com.google.android.material.transition.MaterialFadeThrough
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.listeners.addClickListener
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.listeners.addClickListener
import com.mikepenz.fastadapter.paged.ExperimentalPagedSupport
import com.mikepenz.fastadapter.paged.PagedModelAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@ExperimentalPagedSupport
@AndroidEntryPoint
internal class ArticleListFragment:
    BaseFragment<Nothing, ArticleListViewModel.State, ArticleListViewModel.News>(R.layout.fragment_article_list) {

    private val binding: FragmentArticleListBinding by viewBinding(FragmentArticleListBinding::bind)

    override val mViewModel: ArticleListViewModel by viewModels()

    private val asyncDifferConfig =
        AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }).build()

    private val itemAdapter = PagedModelAdapter<Article, ArticleItemView>(asyncDifferConfig) {
        ArticleItemView(it)
    }

    private val headerAdapter = ItemAdapter<ArticleHeaderItemView>()

    private var fastAdapter = FastAdapter.with(listOf(headerAdapter, itemAdapter))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
        )

        binding.articles.apply {
            adapter = fastAdapter
            addItemDecoration(dividerItemDecoration)
        }

        fastAdapter.addThrottlingClickListener(
            resolveView = { binding: ItemArticleBinding ->
                binding.root
            },
            onClick = { _, _, _, item ->
                val mItem = item as ArticleItemView
                findNavController().navigate(
                    ArticleListFragmentDirections.listToDetails(
                        image = mItem.article.urlToImage,
                        title = mItem.article.title,
                        description = mItem.article.content,
                        category = mItem.article.source.name
                    )
                )
            }
        )

        fastAdapter.addThrottlingClickListener(
            resolveView = { binding: HeaderArticleBinding ->
                binding.root
            },
            onClick = { _, _, _, item ->
                val mItem = item as ArticleHeaderItemView
                findNavController().navigate(
                    ArticleListFragmentDirections.listToDetails(
                        image = mItem.article.urlToImage,
                        title = mItem.article.title,
                        description = mItem.article.description,
                        category = mItem.article.source.name
                    )
                )
            }
        )
    }

    override fun bindStates(binder: CoBinder<ArticleListViewModel.State>) = binder.run {
        on { it.loading } bindNonNull { loading ->
            binding.loading.isVisible = loading
        }

        on { it.articles } bindNonNull { data ->
            if (data.isNotEmpty()) {
                data[0]?.let { header ->
                    val result = FastAdapterDiffUtil.calculateDiff(
                        headerAdapter,
                        listOf(ArticleHeaderItemView(header))
                    )
                    FastAdapterDiffUtil[headerAdapter] = result
                }
            }

            itemAdapter.submitList(data)
        }
    }

    override fun presentNews(news: ArticleListViewModel.News) {
        when (news) {
            is ArticleListViewModel.News.ShowError -> requireContext().showError(news.error)
        }
    }
}