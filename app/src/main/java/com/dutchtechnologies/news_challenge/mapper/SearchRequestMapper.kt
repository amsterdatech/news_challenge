package com.dutchtechnologies.news_challenge.mapper

import com.dutchtechnologies.domain.model.SearchRequest
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import javax.inject.Inject

open class SearchRequestMapper @Inject constructor() : Mapper<SearchRequestForm?, SearchRequest> {


    override fun mapToView(type: SearchRequest): SearchRequestForm {
        return SearchRequestForm()
    }

    override fun mapFromView(type: SearchRequestForm?): SearchRequest {
        type?.let {
            return SearchRequest(
                apiKey = type.apiKey,
                sources = type.sources,
                pageIndex = type.pageIndex
            )
        }

        return SearchRequest()
    }
}