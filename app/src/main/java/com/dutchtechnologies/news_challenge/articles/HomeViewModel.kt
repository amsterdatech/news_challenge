package com.dutchtechnologies.news_challenge.articles

import android.arch.lifecycle.*
import com.dutchtechnologies.domain.interactor.GetSourcesListSingleUseCase
import com.dutchtechnologies.news_challenge.BuildConfig
import com.dutchtechnologies.news_challenge.base.ViewData
import com.dutchtechnologies.news_challenge.mapper.SearchRequestMapper
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import com.dutchtechnologies.news_challenge.model.Source
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class HomeViewModel @Inject constructor(private var getSourcesUseCase: GetSourcesListSingleUseCase) :
    ViewModel(), LifecycleObserver {


    @Inject
    lateinit var mapper: SearchRequestMapper

    private val compositeDisposable = CompositeDisposable()

    private val liveDataSources: MutableLiveData<ViewData<List<Source>>> = MutableLiveData()

    lateinit var searchRequest: SearchRequestForm

    fun liveDataSources() = liveDataSources

    fun loadSources(searchRequest: SearchRequestForm?) {
        searchRequest?.pageIndex?.let {
            if (it == 1) {
                liveDataSources.value =
                    ViewData(ViewData.Status.LOADING)
            }
        }

        getSourcesUseCase
            .execute(
                SourceSubscriber(), mapper.mapFromView(searchRequest)
            )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun shouldFetch() {
        if (liveDataSources.value == null) {
            searchRequest = SearchRequestForm(
                apiKey = BuildConfig.API_KEY
            )
            loadSources(searchRequest)
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


    internal fun handleSuccess(results: List<Source>) {
        liveDataSources.value =
            ViewData(
                ViewData.Status.SUCCESS,
                results
            )
    }


    private fun handleError(exception: Throwable) {
        liveDataSources.value =
            ViewData(
                ViewData.Status.ERROR,
                error = exception
            )

    }

    private fun mapFromDomainToView(results: List<com.dutchtechnologies.domain.Source>): List<com.dutchtechnologies.news_challenge.model.Source> {
        return results.map {
            com.dutchtechnologies.news_challenge.model.Source(
                it.id,
                it.name,
                it.category,
                it.url
            )
        }
    }

    inner class SourceSubscriber :
        DisposableSingleObserver<List<com.dutchtechnologies.domain.Source>>() {


        override fun onSuccess(t: List<com.dutchtechnologies.domain.Source>) {
            handleSuccess(mapFromDomainToView(t))
        }

        override fun onError(exception: Throwable) {
            handleError(exception)
        }

    }

}
