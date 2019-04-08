package com.dutchtechnologies.news_challenge.articles

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dutchtechnologies.news_challenge.BuildConfig
import com.dutchtechnologies.news_challenge.R
import com.dutchtechnologies.news_challenge.base.BaseFragment
import com.dutchtechnologies.news_challenge.fragmentAddToBackStack
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import com.dutchtechnologies.news_challenge.model.Source
import com.dutchtechnologies.news_challenge.presentation.ArticlesContract
import com.dutchtechnologies.news_challenge.presentation.SourcesPresenter
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.fragment_source.view.*
import javax.inject.Inject


class SourcesFragment : BaseFragment(), View.OnClickListener, ArticlesContract.SourceView {

    //    private lateinit var homeViewModel: HomeViewModel
    private val sourcesAdapter = SourcesAdapter()

    @Inject
    lateinit var sourcesPresenter: SourcesPresenter


    companion object {
        fun newInstance(): SourcesFragment {
            return SourcesFragment()
        }

    }

    override fun layoutResource(): Int = R.layout.fragment_source

    override fun setupView(view: View) {
        (activity as HomeActivity).setSupportActionBar(view.fragment_sources_toolbar)
        (activity as HomeActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        view.fragment_sources_toolbar.title = "News App"

        val linearLayoutManager = LinearLayoutManager(activity)
        view.fragment_sources_recycler_view.layoutManager = linearLayoutManager
        view.fragment_sources_recycler_view.setHasFixedSize(true)
        view.fragment_sources_recycler_view.addItemDecoration(
            DividerItemDecoration(
                view.context,
                R.drawable.list_divider
            )
        )


        view.fragment_sources_recycler_view.adapter = sourcesAdapter
        sourcesAdapter.click = this

//        Handler().postDelayed({
//            fragment_sources_custom_view_loading.visibility = View.GONE
//            fragment_sources_recycler_view.visibility = View.VISIBLE
//
//            sourcesAdapter.items = getSources()
//
//        }, 1500)
    }

    override fun screenName(): String? = ""


    fun getSources(): List<Source> {
        val itineraries = mutableListOf<Source>()
        for (i in 1..30) {
            val itinerary = Source(
                listOf("bbc-news", "cnn", "bloomberg").shuffled().take(1)[0],
                listOf("BBC", "Bloomberg", "CNN").shuffled().take(1)[0],
                "News for up-to-the-minute news, breaking news, video, audio and feature stories.",
                listOf(
                    "http://www.bbc.co.uk/news",
                    "http://www.abc.net.au/news",
                    "http://www.aljazeera.com",
                    "http://www.spiegel.de",
                    "http://us.cnn.com",
                    "http://espn.go.com",
                    "http://news.nationalgeographic.com"
                ).shuffled().take(1)[0]
            )
            itineraries.add(itinerary)
        }

        return itineraries
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.view_holder_sources_parent -> {
                val viewHolder = view?.tag as RecyclerView.ViewHolder
                val position = viewHolder.adapterPosition
                // viewHolder.getItemId();
                // viewHolder.getItemViewType();
                // viewHolder.itemView;
                val currentSource = sourcesAdapter.items[position]
                (activity as HomeActivity).fragmentAddToBackStack(
                    R.id.home_container,
                    NewsFragment.newInstance(
                        currentSource.id,
                        currentSource.title
                    )
                )
            }

        }
    }


    override fun onStart() {
        super.onStart()
        sourcesPresenter.attachView(this)
        sourcesPresenter.start()

        if (sourcesAdapter.items == null || sourcesAdapter.items.isEmpty()) {
            sourcesPresenter.getSources(
                SearchRequestForm(
                    apiKey = BuildConfig.API_KEY
                )
            )
        }

    }

    override fun onStop() {
        super.onStop()
        sourcesPresenter.stop()
    }

    override fun showResults(results: List<Source>) {
        sourcesAdapter.items += results
        fragment_sources_recycler_view.visibility = View.VISIBLE
    }

    override fun setPresenter(presenter: ArticlesContract.SourcesPresenter) {
    }

    override fun showProgress() {
        fragment_sources_custom_view_loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        fragment_sources_custom_view_loading.visibility = View.GONE
    }

    override fun hideResults() {
        fragment_sources_recycler_view.visibility = View.GONE
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

