package com.newscheck

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.newscheck.repositories.NetworkStatusRepository
import com.newscheck.utill.getNetworkStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NetworkReceiver : BroadcastReceiver() {

    var netWorkStatusRepository: NetworkStatusRepository? = null
        @Inject set

    override fun onReceive(context: Context?, intent: Intent?) {
        netWorkStatusRepository?.updateNetworkStatus(context?.getNetworkStatus() ?: false)
    }

}