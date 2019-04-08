package com.dutchtechnologies.news_challenge.base

class ViewData<D>(val status: Status, val data: D? = null, val error: Throwable? = null) {
    enum class Status {
        LOADING, SUCCESS, COMPLETE, ERROR
    }
}