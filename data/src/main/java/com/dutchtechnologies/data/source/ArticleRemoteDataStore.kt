package com.dutchtechnologies.data.source

import com.dutchtechnologies.data.model.ArticleEntity
import com.dutchtechnologies.data.model.SourceEntity
import com.dutchtechnologies.data.remote.ArticleRemoteImpl
import com.dutchtechnologies.data.remote.SearchForm
import com.dutchtechnologies.data.repository.ArticleDataStore
import io.reactivex.Single
import javax.inject.Inject

class ArticleRemoteDataStore @Inject constructor(private val remote: ArticleRemoteImpl) :
    ArticleDataStore {

    override fun getArticles(searchForm: SearchForm): Single<List<ArticleEntity>> {
        return remote.getArticles(searchForm)
    }

    override fun getSources(searchForm: SearchForm): Single<List<SourceEntity>> {
        return remote.getSources(searchForm)
    }

}
