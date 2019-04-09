package com.dutchtechnologies.news_challenge.articles

import android.arch.lifecycle.*
import com.dutchtechnologies.domain.interactor.GetArticlesListSingleUseCase
import com.dutchtechnologies.domain.interactor.GetSourcesListSingleUseCase
import com.dutchtechnologies.news_challenge.BuildConfig
import com.dutchtechnologies.news_challenge.base.ViewData
import com.dutchtechnologies.news_challenge.mapper.SearchRequestMapper
import com.dutchtechnologies.news_challenge.model.Article
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import com.dutchtechnologies.news_challenge.model.Source
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private var getSourcesUseCase: GetSourcesListSingleUseCase,
    private var getArticlesListSingleUseCase: GetArticlesListSingleUseCase
) :
    ViewModel(), LifecycleObserver {


    @Inject
    lateinit var mapper: SearchRequestMapper

    private val liveDataSources: MutableLiveData<ViewData<List<Source>>> = MutableLiveData()

    private val liveDataArticles: MutableLiveData<ViewData<List<Article>>> = MutableLiveData()

    private lateinit var searchRequest: SearchRequestForm

    fun liveDataSources() = liveDataSources
    fun liveDataArticles() = liveDataArticles


    private fun loadSources(searchRequest: SearchRequestForm?) {
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

    fun loadArticles(searchRequest: SearchRequestForm?) {

        searchRequest?.pageIndex?.let {
            if (it == 1) {
                liveDataArticles.value = ViewData(ViewData.Status.LOADING)
            }
        }


        getArticlesListSingleUseCase
            .execute(
                ArticleSubscriber(),
                mapper
                    .mapFromView(searchRequest)
            )
    }


    override fun onCleared() {
        super.onCleared()
        getArticlesListSingleUseCase.dispose()
        getSourcesUseCase.dispose()
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

    private fun mapArticleFromDomainToView(results: List<com.dutchtechnologies.domain.Article>): List<com.dutchtechnologies.news_challenge.model.Article> {
        return results.map {
            Article(it.title, it.desc, it.urlToImage, it.author, it.publishedAt, it.url)
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

    inner class ArticleSubscriber :
        DisposableSingleObserver<List<com.dutchtechnologies.domain.Article>>() {


        override fun onSuccess(t: List<com.dutchtechnologies.domain.Article>) {
            liveDataArticles.value = ViewData(ViewData.Status.SUCCESS, mapArticleFromDomainToView(t))
        }

        override fun onError(exception: Throwable) {
            liveDataArticles.value = ViewData(ViewData.Status.ERROR, error = exception)

        }

    }

}
