package com.dutchtechnologies.data.repository

import com.dutchtechnologies.data.model.ArticleEntity
import com.dutchtechnologies.data.model.SourceEntity
import io.reactivex.Single

interface ArticleCache {

    fun getArticles(): Single<List<ArticleEntity>>

    fun getSources(): Single<List<SourceEntity>>

    fun isCached(): Boolean

    fun setLastCacheTime(lastCache: Long)

    fun isExpired(): Boolean

}
