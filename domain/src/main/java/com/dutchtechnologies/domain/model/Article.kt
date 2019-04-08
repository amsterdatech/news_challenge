package com.dutchtechnologies.domain

data class Article(
    var author: String = "",
    var title: String = "",
    var desc: String = "",
    var url: String = "",
    var urlToImage: String = "",
    var publishedAt: String = ""
)
