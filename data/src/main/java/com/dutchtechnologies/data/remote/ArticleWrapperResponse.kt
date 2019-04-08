package com.dutchtechnologies.data.remote

class ArticleWrapperResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)


data class Article(
    var author: String = "",
    var description: String = "",
    var publishedAt: String = "",
    var source: Source,
    var title: String = "",
    var url: String = "",
    var urlToImage: String = ""
)

data class Source(
    var id: String = "",
    var name: String = ""
)