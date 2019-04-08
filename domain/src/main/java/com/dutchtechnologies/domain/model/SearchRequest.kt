package com.dutchtechnologies.domain.model

data class SearchRequest(
    val apiKey: String = "",
    val sources:String = "",
    var pageIndex:Int = 1
)