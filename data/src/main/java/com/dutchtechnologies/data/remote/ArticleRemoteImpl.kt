package com.dutchtechnologies.data.remote

import com.dutchtechnologies.data.model.ArticleEntity
import com.dutchtechnologies.data.model.SourceEntity
import com.dutchtechnologies.data.repository.ArticleRemote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import javax.inject.Inject

class ArticleRemoteImpl @Inject constructor(
    private val gson: Gson,
    private val service: NewsApiService,
    private val entityMapper: ArticleEntityMapper,
    private val sourceMapper: SourceEntityMapper
) :
    ArticleRemote {

    override fun getArticles(searchForm: SearchForm): Single<List<ArticleEntity>> {
        //searchForm
        return service
            .getArticles(searchForm.sources ?: "", searchForm.apiKey, searchForm.pageIndex)
            .map {
                entityMapper.mapFromRemote(it)
            }
    }

    override fun getSources(searchForm: SearchForm): Single<List<SourceEntity>> {
        //searchForm
        return service
            .getSources(searchForm.apiKey, searchForm.pageIndex)
            .map {
                sourceMapper.mapFromRemote(it)
            }
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}