package com.dutchtechnologies.data.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/everything")
    fun getArticles(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String,
        @Query("page") pageIndex: Int = 1,
        @Query("pageSize") pageSize: Int = 25
    ): Single<ArticleWrapperResponse>

    @GET("v2/sources")
    fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("page") pageIndex: Int = 1,
        @Query("pageSize") pageSize: Int = 100
    ): Single<SourceWrapperResponse>

}


