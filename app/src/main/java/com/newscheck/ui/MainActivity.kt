package com.newscheck.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.newscheck.R
import com.newscheck.ui.onboarding.OnboardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, OnboardingFragment())
            .commit()
    }
}