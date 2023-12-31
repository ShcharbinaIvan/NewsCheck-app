package com.newscheck.ui

import android.Manifest
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.newscheck.NetworkReceiver
import com.newscheck.R
import com.newscheck.ui.onboarding.OnboardingFragment
import com.newscheck.ui.signin.SignInFragment
import com.newscheck.utill.getNetworkStatus
import com.newscheck.worker.NotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val receiver = NetworkReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (viewModel.getIsFirstOpen()) {
            viewModel.setFirstOpen(false)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OnboardingFragment())
                .commit()
        } else {
            logInCheck(viewModel.getIsUserLogin())
        }
        networkStatus(receiver)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequestBuilder =
            PeriodicWorkRequestBuilder<NotificationWorker>(
                1,
                TimeUnit.DAYS
            )
                .addTag("NotificationWorker")
                .setConstraints(constraints)
                .build()

        val notificationLaunch = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granded ->
            if (granded) {
                if (WorkManager.getInstance(this).getWorkInfosByTag(workRequestBuilder.tags.toString()).isDone) {
                    WorkManager.getInstance(this).enqueue(workRequestBuilder)
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationLaunch.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }

    private fun logInCheck(logIn: Boolean) {
        if (logIn) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NavigationFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SignInFragment())
                .commit()
        }
    }

    @Suppress("DEPRECATION")
    private fun networkStatus(receiver: NetworkReceiver) {
        val filter = IntentFilter().apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        }
        registerReceiver(receiver, filter)
        viewModel.updateNetworkStatus(this.getNetworkStatus())
    }

}