package com.newscheck.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.newscheck.model.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

}