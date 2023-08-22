package com.newscheck.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.newscheck.model.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert
    suspend fun saveNews(news: NewsEntity)

    @Query("SELECT * FROM News where email = :email")
    suspend fun getNews(email:String): List<NewsEntity>

    @Delete
    suspend fun deleteNews(news: NewsEntity)

}