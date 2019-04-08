package com.dutchtechnologies.news_challenge.articles

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: String
)