package com.newscheck.repositories

import com.newscheck.db.NewsDao
import com.newscheck.model.News
import com.newscheck.model.entity.NewsEntity
import javax.inject.Inject

class LikeNewsRepository @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend fun getNews(): ArrayList<News> {
        return (newsDao.getNews().map {
            News(
                it.category,
                it.description,
                it.image,
                it.published_at,
                it.source,
                it.title,
                it.url
            )
        } as ArrayList<News>)
    }

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

    suspend fun deleteNews(news: News) {
        newsDao.deleteNews(
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