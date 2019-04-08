package com.dutchtechnologies.domain

import com.dutchtechnologies.domain.model.SearchRequest
import io.reactivex.Single

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */

interface ArticleRepository {
    fun getArticles(request: SearchRequest?): Single<List<Article>>

    fun getSources(request: SearchRequest?): Single<List<Source>>

}