package com.newscheck.repositories

import com.google.firebase.auth.FirebaseAuth
import com.newscheck.db.NewsDao
import com.newscheck.model.News
import com.newscheck.model.entity.NewsEntity
import javax.inject.Inject

class LikeNewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val auth: FirebaseAuth
) {

    suspend fun getNews(email:String): ArrayList<News> {
        return (newsDao.getNews(email).map {
            News(
                it.id,
                it.email,
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

    suspend fun deleteNews(news: News) {
        newsDao.deleteNews(
            NewsEntity(
                news.id,
                news.email,
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

    fun getEmail(): String {
        return auth.currentUser?.email.toString()
    }

}