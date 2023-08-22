@file:Suppress("DEPRECATION")

package com.newscheck.utill

import android.content.Context
import android.net.ConnectivityManager

fun Context.getNetworkStatus(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}