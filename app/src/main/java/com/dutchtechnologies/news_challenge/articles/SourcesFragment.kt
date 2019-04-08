package com.dutchtechnologies.news_challenge.articles

import android.graphics.Color
import android.os.Build
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity?.window?.statusBarColor = context?.resources?.getColor(R.color.colorPrimary, null)
                ?: Color.parseColor("#006200EE")
        }

        view.fragment_sources_toolbar.title = context?.resources?.getString(R.string.app_name)

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

