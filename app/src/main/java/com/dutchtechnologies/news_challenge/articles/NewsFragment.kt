package com.dutchtechnologies.news_challenge.articles

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dutchtechnologies.news_challenge.BuildConfig
import com.dutchtechnologies.news_challenge.R
import com.dutchtechnologies.news_challenge.base.BaseFragment
import com.dutchtechnologies.news_challenge.extensions.Browser
import com.dutchtechnologies.news_challenge.model.Article
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import com.dutchtechnologies.news_challenge.popBackStack
import com.dutchtechnologies.news_challenge.presentation.ArticlesContract
import com.dutchtechnologies.news_challenge.presentation.ArticlesPresenter
import extra
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import javax.inject.Inject

class NewsFragment : BaseFragment(), View.OnClickListener, ArticlesContract.View {
    lateinit var newsAdapter: NewsAdapter
    private var slug: String? = null
    private var name: String? = null

    @Inject
    lateinit var articlesPresenter: ArticlesPresenter


    companion object {

        const val EXTRA_SLUG = "extra_slug"
        const val EXTRA_NAME = "extra_name"

        fun newInstance(slug: String? = null, name: String? = null): NewsFragment {
            return NewsFragment().let {
                val bundle = Bundle()
                bundle.putString(EXTRA_SLUG, slug)
                bundle.putString(EXTRA_NAME, name)

                it.arguments = bundle
                return@let it
            }
        }
    }

    override fun layoutResource(): Int = R.layout.fragment_news

    override fun setupView(view: View) {
        slug = extra(EXTRA_SLUG, "")
        name = extra(EXTRA_NAME, "")


        (activity as HomeActivity).setSupportActionBar(view.fragment_articles_toolbar)
        view.fragment_articles_toolbar.setNavigationOnClickListener(this)

        (activity as HomeActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        view.fragment_articles_toolbar.title = name

        val linearLayoutManager = LinearLayoutManager(activity)
        view.fragment_articles_recycler_view.layoutManager = linearLayoutManager
        view.fragment_articles_recycler_view.addItemDecoration(
            DividerItemDecoration(
                view.context,
                R.drawable.list_divider
            )
        )

        newsAdapter = NewsAdapter()
        view.fragment_articles_recycler_view.adapter = newsAdapter
        newsAdapter.click = this


        Browser.warm((activity as HomeActivity).baseContext)

    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.view_holder_regular_parent, R.id.view_holder_headline_parent -> {
                val viewHolder = view?.tag as RecyclerView.ViewHolder
                val position = viewHolder.adapterPosition
                val article = newsAdapter.items[position]
                Browser.openIntern((activity as HomeActivity).baseContext, article.url)

            }

            else -> {
                activity?.popBackStack()
            }
        }
    }

    override fun screenName(): String? = ""


    override fun onStart() {
        super.onStart()
        articlesPresenter.attachView(this)
        articlesPresenter.start()

        if (newsAdapter.items == null || newsAdapter.items.isEmpty()) {
            articlesPresenter.search(
                SearchRequestForm(
                    apiKey = BuildConfig.API_KEY, sources = slug ?: ""
                )
            )
        }

    }

    override fun onStop() {
        super.onStop()
        articlesPresenter.stop()
    }

    override fun onDestroy() {
        Browser.cool( (activity as HomeActivity).baseContext)
        super.onDestroy()
    }

    override fun showResults(results: List<Article>) {
        newsAdapter.items += results
        fragment_articles_recycler_view.visibility = View.VISIBLE

    }

    override fun setPresenter(presenter: ArticlesContract.ArticlesPresenter) {
        articlesPresenter = presenter as ArticlesPresenter
    }

    override fun showProgress() {
        fragment_articles_custom_view_loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        fragment_articles_custom_view_loading.visibility = View.GONE
    }

    override fun hideResults() {
        fragment_articles_recycler_view.visibility = View.GONE
    }

    override fun showErrorState() {
    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }
}