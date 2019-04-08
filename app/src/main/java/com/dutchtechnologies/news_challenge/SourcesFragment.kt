package com.dutchtechnologies.news_challenge

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.fragment_source.view.*

class SourcesFragment : BaseFragment(), View.OnClickListener {
    //    private lateinit var homeViewModel: HomeViewModel
    private val sourcesAdapter = SourcesAdapter()


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

        view.fragment_sources_recycler_view.addItemDecoration(DividerItemDecoration(view.context, R.drawable.list_divider))


        view.fragment_sources_recycler_view.adapter = sourcesAdapter
        sourcesAdapter.click = this


        Handler().postDelayed({
            fragment_sources_custom_view_loading.visibility = View.GONE
            fragment_sources_recycler_view.visibility = View.VISIBLE

            sourcesAdapter.items = getSources()

        }, 1500)
    }

    override fun screenName(): String? = ""


    fun getSources(): List<Source> {
        val itineraries = mutableListOf<Source>()
        for (i in 1..100) {
            val itinerary = Source(
                listOf("BBC", "Bloomberg", "CNN").shuffled().take(1)[0],
                "News for up-to-the-minute news, breaking news, video, audio and feature stories.",
                "http://www.bbc.co.uk/news"
            )
            itineraries.add(itinerary)
        }

        return itineraries
    }
}

