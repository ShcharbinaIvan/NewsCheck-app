package com.newscheck.model

data class News(
    val author: String,
    val description: String,
    val image: String,
    val published_at: String,
    val title: String,
    val url: String
)