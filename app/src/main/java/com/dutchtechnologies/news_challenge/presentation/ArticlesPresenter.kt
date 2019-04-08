package com.dutchtechnologies.news_challenge.presentation

import android.util.Log
import com.dutchtechnologies.domain.Article
import com.dutchtechnologies.domain.interactor.SingleUseCase
import com.dutchtechnologies.domain.model.SearchRequest
import com.dutchtechnologies.news_challenge.mapper.SearchRequestMapper
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import com.dutchtechnologies.news_challenge.model.Article as ArticleView


class ArticlesPresenter @Inject constructor(
    private val getArticlesUseCase: SingleUseCase<List<Article>, SearchRequest>,
    private val mapper: SearchRequestMapper
) :
    ArticlesContract.ArticlesPresenter {

    lateinit var view: ArticlesContract.View


    fun attachView(view: Any?) {
        this.view = view as ArticlesContract.View
    }

    override fun start() {
    }

    override fun stop() {
        getArticlesUseCase.dispose()
    }

    override fun search(searchRequest: SearchRequestForm?) {

        searchRequest?.pageIndex?.let {
            if (it == 1) {
                view.showProgress()
            } else {
//                view.showResultLoading()
            }
        }

        getArticlesUseCase
            .execute(
                ArticleSubscriber(), mapper.mapFromView(searchRequest)
            )
    }

    internal fun handleSuccess(results: List<Article>) {
        resetViewState()

        if (results.isNotEmpty()) {
            view.showResults(mapFromDomainToView(results))

        } else {
            view.hideResults()
            view.showEmptyState()
        }
    }

    private fun mapFromDomainToView(results: List<Article>): List<com.dutchtechnologies.news_challenge.model.Article> {
        return results.map {
            ArticleView(it.title, it.desc, it.urlToImage, it.author, it.publishedAt, it.url)
        }
    }

    inner class ArticleSubscriber : DisposableSingleObserver<List<Article>>() {

        override fun onSuccess(t: List<Article>) {
            handleSuccess(t)
        }

        override fun onError(exception: Throwable) {
            resetViewState()

            if (exception is IOException) {

            }

            if (exception is HttpException) {
                when (exception.code()) {
                    426 -> {
                        Log.d("ENDLESS END X)", "${exception.message()}")
                    }
//                    401 -> {
//                    }
//                    404 -> {
//                        when (action) {
//                            false -> markAsRemoved()
//                            true -> markAsAdded()
//                        }
//                    }
//                    408 -> {
//                        dialogServerError()
//                    }
//                    500 -> {
//                        dialogServerError()
//                    }
                    else -> {
                        view.showErrorState()
                    }
                }
            }

        }

    }

    private fun resetViewState() {
        view.hideErrorState()
        view.hideEmptyState()
        view.hideProgress()
    }
}