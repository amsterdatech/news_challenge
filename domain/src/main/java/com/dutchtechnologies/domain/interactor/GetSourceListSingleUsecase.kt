package com.dutchtechnologies.domain.interactor

import com.dutchtechnologies.domain.ArticleRepository
import com.dutchtechnologies.domain.Source
import com.dutchtechnologies.domain.model.SearchRequest
import io.reactivex.Scheduler
import javax.inject.Inject

class GetSourcesListSingleUseCase @Inject constructor(
    private val repository: ArticleRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : SingleUseCase<List<Source>, SearchRequest>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(params: SearchRequest?) = repository.getSources(params)

}
