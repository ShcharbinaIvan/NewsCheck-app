package com.newscheck.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.newscheck.R
import com.newscheck.repositories.ProfileRepository
import com.newscheck.ui.onboarding.OnboardingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var profileRepository: ProfileRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (profileRepository.isUserLogin()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NavigationFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OnboardingFragment())
                .commit()
        }

    }
}