package com.dutchtechnologies.news_challenge.presentation

import com.dutchtechnologies.domain.Source
import com.dutchtechnologies.domain.interactor.SingleUseCase
import com.dutchtechnologies.domain.model.SearchRequest
import com.dutchtechnologies.news_challenge.mapper.SearchRequestMapper
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class SourcesPresenter @Inject constructor(
    private val getSourcesUseCase: SingleUseCase<List<Source>, SearchRequest>,
    private val mapper: SearchRequestMapper
) :
    ArticlesContract.SourcesPresenter {


    lateinit var view: ArticlesContract.SourceView


    fun attachView(view: Any?) {
        this.view = view as ArticlesContract.SourceView
    }

    override fun start() {
    }

    override fun stop() {
        getSourcesUseCase.dispose()
    }

    override fun getSources(searchRequest: SearchRequestForm?) {

        searchRequest?.pageIndex?.let {
            if (it == 1) {
                view.showProgress()
            } else {
//                view.showResultLoading()
            }
        }

        getSourcesUseCase
            .execute(
                SourceSubscriber(), mapper.mapFromView(searchRequest)
            )
    }

    internal fun handleSuccess(results: List<Source>) {
        resetViewState()

        if (results.isNotEmpty()) {
            view.showResults(mapFromDomainToView(results))

        } else {
            view.hideResults()
            view.showEmptyState()
        }
    }

    private fun mapFromDomainToView(results: List<Source>): List<com.dutchtechnologies.news_challenge.model.Source> {
        return results.map {
            com.dutchtechnologies.news_challenge.model.Source(
                it.id,
                it.name,
                it.category,
                it.url
            )
        }
    }

    inner class SourceSubscriber : DisposableSingleObserver<List<Source>>() {

        override fun onSuccess(t: List<Source>) {
            handleSuccess(t)
        }

        override fun onError(exception: Throwable) {
            resetViewState()
            view.showErrorState()
        }

    }

    private fun resetViewState() {
        view.hideErrorState()
        view.hideEmptyState()
        view.hideProgress()
    }
}