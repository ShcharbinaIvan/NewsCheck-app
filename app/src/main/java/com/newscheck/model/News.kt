package com.newscheck.model

data class News(
    val category: String,
    val description: String,
    val image: String,
    val published_at: String,
    val source: String,
    val title: String,
    val url: String
)