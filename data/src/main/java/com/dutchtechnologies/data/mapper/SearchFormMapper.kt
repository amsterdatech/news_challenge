package com.dutchtechnologies.data.mapper

import com.dutchtechnologies.data.remote.SearchForm
import com.dutchtechnologies.domain.model.SearchRequest
import javax.inject.Inject


open class SearchFormMapper @Inject constructor() : Mapper<SearchForm, SearchRequest> {

    override fun mapFromEntity(type: SearchForm): SearchRequest {
        return SearchRequest(
            type.apiKey, type.sources ?: "", type.pageIndex
        )
    }

    override fun mapToEntity(type: SearchRequest): SearchForm {
        return SearchForm(
            type.apiKey, type.sources, type.pageIndex
        )
    }


}