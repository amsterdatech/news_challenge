package com.dutchtechnologies.domain.interactor

import com.dutchtechnologies.domain.Article
import com.dutchtechnologies.domain.ArticleRepository
import com.dutchtechnologies.domain.model.SearchRequest
import io.reactivex.Scheduler
import javax.inject.Inject

class GetArticlesListSingleUseCase @Inject constructor(
    private val repository: ArticleRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : SingleUseCase<List<Article>, SearchRequest>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(params: SearchRequest?) = repository.getArticles(params)

}