package com.dutchtechnologies.data.source

import com.dutchtechnologies.data.model.ArticleEntity
import com.dutchtechnologies.data.model.SourceEntity
import com.dutchtechnologies.data.remote.SearchForm
import com.dutchtechnologies.data.repository.ArticleCache
import com.dutchtechnologies.data.repository.ArticleDataStore
import io.reactivex.Single

class ArticleCacheDataStore constructor(private val cache: ArticleCache) :
    ArticleDataStore {
    override fun getSources(searchFrom: SearchForm): Single<List<SourceEntity>> {
        return cache.getSources()
    }

    override fun getArticles(searchForm: SearchForm): Single<List<ArticleEntity>> {
        return cache.getArticles()
    }
}
