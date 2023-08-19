package com.newscheck.repositories

import com.newscheck.db.NewsDao
import com.newscheck.model.News
import com.newscheck.model.entity.NewsEntity
import javax.inject.Inject

class OneNewsRepository @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend fun saveNews(news: News) {
        newsDao.saveNews(
            NewsEntity(
                0,
                news.category,
                news.description,
                news.image,
                news.published_at,
                news.source,
                news.title,
                news.url
            )
        )
    }

}