package com.dutchtechnologies.data.remote

import com.dutchtechnologies.data.model.ArticleEntity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

open class ArticleEntityMapper @Inject constructor() : EntityMapper<ArticleWrapperResponse, List<ArticleEntity>> {


    override fun mapFromRemote(type: ArticleWrapperResponse): List<ArticleEntity> {
        return type.articles.map {
            ArticleEntity(it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt)
        }
    }


    /**
     * Pattern: yyyy-MM-dd HH:mm:ss
     */
    fun Date.formatToServerDateTimeDefaults(): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(this)
    }


}