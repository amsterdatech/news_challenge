package com.dutchtechnologies.data.remote

data class SourceWrapperResponse(
    val sources: List<SourceItem>,
    val status: String
)

data class SourceItem(
    val category: String,
    val country: String,
    val description: String,
    val id: String,
    val language: String,
    val name: String,
    val url: String
)
