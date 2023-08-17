package com.newscheck.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val category: String,
    val description: String,
    val image: String,
    val published_at: String,
    val source: String,
    val title: String,
    val url: String
) : Parcelable