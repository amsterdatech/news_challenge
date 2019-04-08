package com.dutchtechnologies.news_challenge.presentation

import com.dutchtechnologies.news_challenge.model.Article
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import com.dutchtechnologies.news_challenge.model.Source

interface ArticlesContract {

    interface View : BaseView<ArticlesPresenter> {
        fun showResults(results: List<Article>)
    }

    interface SourceView : BaseView<SourcesPresenter> {
        fun showResults(results: List<Source>)
    }


    interface BasePresenter {
        fun start()

        fun stop()
    }

    interface BaseView<in T : BasePresenter> {
        fun setPresenter(presenter: T)

        fun showProgress()

        fun hideProgress()

        fun hideResults()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface ArticlesPresenter : BasePresenter {
        fun search(searchRequest: SearchRequestForm? = null)
    }

    interface SourcesPresenter : BasePresenter {
        fun getSources(searchRequest: SearchRequestForm? = null)
    }
}