package com.newscheck.repositories

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatusRepository @Inject constructor() {

    val networkStatus = MutableLiveData(true)

}