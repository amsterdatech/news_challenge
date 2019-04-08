package com.dutchtechnologies.news_challenge

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.fragment_source.view.*
import android.support.v7.widget.RecyclerView


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
        view.fragment_sources_recycler_view.setHasFixedSize(true)
        view.fragment_sources_recycler_view.addItemDecoration(
            DividerItemDecoration(
                view.context,
                R.drawable.list_divider
            )
        )


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
        for (i in 1..30) {
            val itinerary = Source(
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
                //Calls NewsActivity or Fragment
                //sourceAdapter[position].slug
                (activity as HomeActivity).fragmentAddToBackStack(R.id.home_container, NewsFragment.newInstance())
            }

        }

    }
}

