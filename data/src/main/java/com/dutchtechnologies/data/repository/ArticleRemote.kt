package com.dutchtechnologies.data.repository

import com.dutchtechnologies.data.model.ArticleEntity
import com.dutchtechnologies.data.model.SourceEntity
import com.dutchtechnologies.data.remote.SearchForm
import io.reactivex.Single

interface ArticleRemote {
    fun getArticles(searchForm: SearchForm): Single<List<ArticleEntity>>
    fun getSources(searchForm: SearchForm): Single<List<SourceEntity>>

}