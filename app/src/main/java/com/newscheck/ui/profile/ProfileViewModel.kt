package com.newscheck.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newscheck.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    var email = ""

    fun logOut() {
        profileRepository.logOut()
    }

    fun getEmail() {
        email = profileRepository.getEmail().toString()
    }

}