package com.newscheck.model.network

import com.newscheck.model.News

data class NewsResponse(
    val news: ArrayList<News>
)