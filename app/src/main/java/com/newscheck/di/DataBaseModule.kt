package com.newscheck.di

import android.content.Context
import androidx.room.Room
import com.newscheck.db.AppDataBase
import com.newscheck.db.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATA_BASE = "data-base"

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providesNews(@ApplicationContext context: Context): NewsDao {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATA_BASE).build().getNewsDao()
    }

}