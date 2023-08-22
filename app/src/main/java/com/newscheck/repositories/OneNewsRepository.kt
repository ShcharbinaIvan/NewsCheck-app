package com.newscheck.repositories

import com.google.firebase.auth.FirebaseAuth
import com.newscheck.db.NewsDao
import com.newscheck.model.News
import com.newscheck.model.entity.NewsEntity
import javax.inject.Inject

class OneNewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val auth: FirebaseAuth
) {

    suspend fun saveNews(news: News) {
        newsDao.saveNews(
            NewsEntity(
                0,
                auth.currentUser?.email.toString(),
                news.category,
                news.description,
                news.image,
                news.publishedAt,
                news.source,
                news.title,
                news.url
            )
        )
    }

}