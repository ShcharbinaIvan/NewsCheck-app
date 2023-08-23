package com.newscheck.repositories

import com.google.firebase.auth.FirebaseAuth
import com.newscheck.db.NewsDao
import com.newscheck.model.News
import com.newscheck.utill.entityToNews
import com.newscheck.utill.newsToEntity
import javax.inject.Inject

class LikeNewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val auth: FirebaseAuth
) {

    suspend fun getNews(email: String): ArrayList<News> {
        return (newsDao.getNews(email).map {
            entityToNews(it)
        } as ArrayList<News>)
    }

    suspend fun deleteNews(news: News) {
        newsDao.deleteNews(
            newsToEntity(news)
        )
    }

    fun getEmail(): String {
        return auth.currentUser?.email.toString()
    }

}