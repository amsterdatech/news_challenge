package com.dutchtechnologies.data.remote

import com.google.gson.annotations.SerializedName

data class SearchForm(
    @SerializedName("apikey") val apiKey: String,
    @SerializedName("sources") var sources: String? = "",
    var pageIndex: Int = 1
)


/*
https://newsapi.org/v2/everything?sources=bbc-news&apiKey=a0db95067dcf4e58bb57743280be666b&page=4&pageSize=25 */

