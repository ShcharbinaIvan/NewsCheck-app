package com.newscheck.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("category")
    val category: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("image")
    val image: String,
    @ColumnInfo("published_at")
    val published_at: String,
    @ColumnInfo("source")
    val source: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("url")
    val url: String
)