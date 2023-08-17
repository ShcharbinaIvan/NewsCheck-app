package com.newscheck.network

import com.newscheck.model.network.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "29c743a02ddbbbd28bccaaf9a5113596"
private const val COUNTRIES = "us"
private const val SOURCES = "cnn"
private const val LIMIT = 100
private const val SORT = "published_desc"

interface NewsApi {

    @GET("news")
    suspend fun getNews(
        @Query("categories") categories: String,
        @Query("access_key") access_key: String = API_KEY,
        @Query("countries") countries: String = COUNTRIES,
        @Query("sources") sources: String = SOURCES,
        @Query("limit") limit: Int = LIMIT,
        @Query("sort") sort: String = SORT,
    ): Response<NewsResponse>
}