package com.dutchtechnologies.data.source

import com.dutchtechnologies.data.repository.ArticleDataStore
import javax.inject.Inject

open class ArticleDataStoreFactory @Inject constructor(
    private val articleRemoteDataStore: ArticleRemoteDataStore

) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): ArticleDataStore {
//        if (itineraryCache.isCached() && !itineraryCache.isExpired()) {
//            return retrieveCacheDataStore()
//        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): ArticleDataStore {
        return articleRemoteDataStore
    }

}
