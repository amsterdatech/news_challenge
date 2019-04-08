package com.dutchtechnologies.data

import com.dutchtechnologies.data.mapper.ArticleMapper
import com.dutchtechnologies.data.mapper.SearchFormMapper
import com.dutchtechnologies.data.mapper.SourceMapper
import com.dutchtechnologies.data.source.ArticleDataStoreFactory
import com.dutchtechnologies.domain.Article
import com.dutchtechnologies.domain.ArticleRepository
import com.dutchtechnologies.domain.Source
import com.dutchtechnologies.domain.model.SearchRequest
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [ArticleRepository] interface for communicating to and from
 * data sources
 */
class ArticleDataRepository @Inject constructor(
    private val factory: ArticleDataStoreFactory,
    private val mapper: ArticleMapper,
    private val sourceMapper: SourceMapper,
    private val searchMapper: SearchFormMapper
) :
    ArticleRepository {


    override fun getArticles(request: SearchRequest?): Single<List<Article>> {

        val dataStore = factory.retrieveDataStore()
        return dataStore
            .getArticles(
                searchMapper
                    .mapToEntity(request ?: SearchRequest())
            )

            .map { list ->
                list.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun getSources(request: SearchRequest?): Single<List<Source>> {

        val dataStore = factory.retrieveDataStore()
        return dataStore
            .getSources(
                searchMapper
                    .mapToEntity(request ?: SearchRequest())
            )
            .map { list ->
                list.map {
                    sourceMapper.mapFromEntity(it)
                }
            }
    }

}
