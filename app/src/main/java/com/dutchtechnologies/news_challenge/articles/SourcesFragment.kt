package com.dutchtechnologies.news_challenge.articles

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dutchtechnologies.news_challenge.R
import com.dutchtechnologies.news_challenge.base.BaseFragment
import com.dutchtechnologies.news_challenge.base.ViewData
import com.dutchtechnologies.news_challenge.extensions.Browser
import com.dutchtechnologies.news_challenge.fragmentAddToBackStack
import com.dutchtechnologies.news_challenge.model.Source
import goneViews
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.fragment_source.view.*
import visible
import withViewModel
import javax.inject.Inject


class SourcesFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    private val sourcesAdapter = SourcesAdapter()

    private val changeObserver = Observer<ViewData<List<Source>>> { value ->
        value?.let {
            when (it?.status) {
                ViewData.Status.LOADING -> {
                    goneViews(
                        fragment_sources_recycler_view
                    )
                    fragment_sources_custom_view_loading.visible()
                }

                ViewData.Status.SUCCESS -> {
                    goneViews(
                        fragment_sources_custom_view_loading
                    )

                    if (sourcesAdapter.items.isNotEmpty()) {
                        fragment_sources_recycler_view.visible()
                        return@let
                    }

                    it.data?.run {
                        sourcesAdapter.items.clear()
                        sourcesAdapter.items.addAll(this)
                        sourcesAdapter.notifyDataSetChanged()
                    }

                }

                ViewData.Status.ERROR -> {
                    goneViews(
                        fragment_sources_custom_view_loading,
                        fragment_sources_recycler_view
                    )
                }
            }
        }
    }


    companion object {
        fun newInstance(): SourcesFragment {
            return SourcesFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        homeViewModel = ViewModelProviders.of(activity as FragmentActivity, viewModelFactory)[HomeViewModel::class.java]
        homeViewModel.liveDataSources().observe(this,changeObserver)
        homeViewModel.shouldFetch()

        return view
    }

    override fun layoutResource(): Int = R.layout.fragment_source

    override fun setupView(view: View) {
        setupToolbar(view)
        setupRecyclerView(view)
        Browser.warm((activity as HomeActivity).baseContext)
    }

    override fun screenName(): String? = ""

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

            R.id.view_holder_sources_url -> {
//                Browser.openIntern((activity as HomeActivity).baseContext, currentSource.url)
            }

        }
    }

    private fun setupRecyclerView(view: View) {
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
    }

    private fun setupToolbar(view: View) {
        (activity as HomeActivity).setSupportActionBar(view.fragment_sources_toolbar)
        (activity as HomeActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity?.window?.statusBarColor = context?.resources?.getColor(R.color.colorPrimary, null)
                ?: Color.parseColor("#006200EE")
        }

        view.fragment_sources_toolbar.title = context?.resources?.getString(R.string.app_name)
    }

}

