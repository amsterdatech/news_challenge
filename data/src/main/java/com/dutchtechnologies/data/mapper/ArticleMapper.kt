package com.dutchtechnologies.data.mapper

import com.dutchtechnologies.data.model.ArticleEntity
import com.dutchtechnologies.domain.Article
import javax.inject.Inject


open class ArticleMapper @Inject constructor(
) :
    Mapper<ArticleEntity, Article> {

    override fun mapFromEntity(type: ArticleEntity): Article {
        return Article(
            type.author?:"",
            type.title?:"",
            type.desc?:"",
            type.url?:"",
            type.urlToImage?:"",
            type.publishedAt?:"")
    }

    override fun mapToEntity(type: Article): ArticleEntity {
        return ArticleEntity(
            type.author,
            type.title,
            type.desc,
            type.url,
            type.urlToImage,
            type.publishedAt
        )
    }
}