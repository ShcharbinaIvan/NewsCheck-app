package com.newscheck.ui

import androidx.lifecycle.ViewModel
import com.newscheck.repositories.NetworkStatusRepository
import com.newscheck.repositories.ProfileRepository
import com.newscheck.repositories.SharedPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val networkStatusRepository: NetworkStatusRepository,
    private val sharedPreferencesRepository: SharedPreferenceRepository
) : ViewModel() {

    fun getIsUserLogin(): Boolean {
        return profileRepository.isUserLogin()
    }

    fun getIsFirstOpen(): Boolean {
        return sharedPreferencesRepository.firstOpenApp()
    }

    fun setFirstOpen(result: Boolean) {
        sharedPreferencesRepository.setFirstOpenApp(result)
    }

    fun updateNetworkStatus(result: Boolean) {
        networkStatusRepository.updateNetworkStatus(result)
    }

}