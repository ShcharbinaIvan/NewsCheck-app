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

    val email1 = MutableLiveData<String>()

    fun logOut() {
        profileRepository.logOut()
    }

    fun getEmail() {
        email1.postValue(profileRepository.getEmail())
    }

}