package com.newscheck.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id:Int,
    val email:String,
    val category: String,
    val description: String,
    val image: String,
    @SerializedName("published_at")
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String
) : Parcelable