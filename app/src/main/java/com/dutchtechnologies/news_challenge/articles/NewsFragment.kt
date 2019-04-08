package com.dutchtechnologies.news_challenge.articles

import addOnScrollListener
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
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

class NewsFragment : BaseFragment(), View.OnClickListener, ArticlesContract.View, NewsAdapter.ScrollListener {
    @Inject
    lateinit var appContext: Context
    private lateinit var newsAdapter: NewsAdapter
    private var slug: String? = null
    private var name: String? = null

    private var toolbarOpacity = 0
    private val acceleratorAlpha = 40

    @Inject
    lateinit var articlesPresenter: ArticlesPresenter

    private var searchRequestForm: SearchRequestForm? = null

    private lateinit var scrollListener: EndlessRecyclerViewScrollListener


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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.statusBarColor = Color.parseColor("#006200EE")
        }

        ViewCompat.setOnApplyWindowInsetsListener(view.fragment_articles_parent) { v, insets ->
            (view.fragment_articles_toolbar.layoutParams as ViewGroup.MarginLayoutParams).topMargin =
                insets.systemWindowInsetTop
            insets.consumeSystemWindowInsets()
        }


        view.fragment_articles_toolbar.title = name


        view.fragment_articles_recycler_view.addOnScrollListener(this)

        val linearLayoutManager = LinearLayoutManager(activity)
        view.fragment_articles_recycler_view.layoutManager = linearLayoutManager
        view.fragment_articles_recycler_view.addItemDecoration(
            DividerItemDecoration(
                view.context,
                R.drawable.list_divider
            )
        )

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                searchRequestForm?.pageIndex = page
                articlesPresenter.search(searchRequestForm)
            }
        }

        view.fragment_articles_recycler_view.addOnScrollListener(scrollListener)

        newsAdapter = NewsAdapter()
        view.fragment_articles_recycler_view.adapter = newsAdapter
        newsAdapter.click = this

        scrollListener.resetState()

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
            searchRequestForm = SearchRequestForm(
                apiKey = BuildConfig.API_KEY, sources = slug ?: ""
            )
            articlesPresenter.search(searchRequestForm)
        }

    }

    override fun onStop() {
        super.onStop()
        fragment_articles_recycler_view.clearOnScrollListeners()
        articlesPresenter.stop()
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
        paintToolbar()
        fragment_articles_custom_view_error_state.visibility = View.VISIBLE
    }

    override fun hideErrorState() {
        fragment_articles_custom_view_error_state.visibility = View.GONE
    }

    override fun showEmptyState() {
        paintToolbar()
        fragment_articles_custom_view_empty_state.visibility = View.VISIBLE
    }

    override fun hideEmptyState() {
        fragment_articles_custom_view_empty_state.visibility = View.GONE
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        var opacity = "ff"
        if (fragment_articles_recycler_view.getChildAt(0).tag is NewsAdapter.HeadlineViewHolder) {

            toolbarOpacity = 255

            if (fragment_articles_recycler_view.getChildAt(0).bottom != 0 && fragment_articles_recycler_view.getChildAt(
                    0
                ).height != 0
            )
                toolbarOpacity -= (fragment_articles_recycler_view.getChildAt(0).bottom *
                        255 / (fragment_articles_recycler_view.getChildAt(0).height))

            if ((toolbarOpacity > 127) && (toolbarOpacity < 255 - acceleratorAlpha)) {
                toolbarOpacity += acceleratorAlpha

            } else if (toolbarOpacity + acceleratorAlpha > 255) {
                toolbarOpacity = 255
            }

            opacity = Integer.toHexString(toolbarOpacity)
        }

        if (opacity.length == 1) {
            opacity = "0$opacity"
        }

        val toolbarColor = "#${opacity}6200EE"
        fragment_articles_toolbar?.setBackgroundColor(Color.parseColor(toolbarColor))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.statusBarColor = Color.parseColor(toolbarColor)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
    }

    private fun paintToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.statusBarColor = Color.parseColor("#FF6200EE")
            view?.fragment_articles_toolbar?.background =
                ColorDrawable(ContextCompat.getColor(appContext, R.color.colorPrimary))

        }
    }
}