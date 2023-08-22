package com.newscheck.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newscheck.repositories.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()

    var openAllNews: (() -> Unit)? = null

    fun signUp(email: String, password: String) {
        isLoading.value = true
        signUpRepository.signUp(email, password, {
            isLoading.value = false
            openAllNews?.invoke()
        }, {
            isLoading.value = false
            error.value = "Email already taken"
        })
    }

}