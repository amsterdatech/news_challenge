package com.dutchtechnologies.data.repository

import com.dutchtechnologies.data.model.ArticleEntity
import com.dutchtechnologies.data.model.SourceEntity
import com.dutchtechnologies.data.remote.SearchForm
import io.reactivex.Single

interface ArticleDataStore {
    fun getArticles(searchFrom: SearchForm): Single<List<ArticleEntity>>
    fun getSources(searchFrom: SearchForm): Single<List<SourceEntity>>

}