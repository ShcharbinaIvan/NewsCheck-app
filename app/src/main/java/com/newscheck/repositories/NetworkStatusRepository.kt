package com.newscheck.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatusRepository @Inject constructor() {

    private val networkStatus = MutableStateFlow(true)

    fun getNetworkState() = networkStatus

    fun updateNetworkStatus(isConnected: Boolean) {
        networkStatus.value = isConnected
    }

}