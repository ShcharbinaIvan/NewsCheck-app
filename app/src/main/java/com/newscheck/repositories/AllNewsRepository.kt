package com.newscheck.repositories

import com.newscheck.network.NewsApi
import javax.inject.Inject

class AllNewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun getNews() = newsApi.getNews()

}