package com.newscheck.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newscheck.repositories.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()

    var openAllNews: (() -> Unit)? = null

    fun login(email: String, password: String) {
        isLoading.value = true
        signInRepository.login(email, password, {
            isLoading.value = false
            openAllNews?.invoke()
        }, {
            isLoading.value = false
            error.value = "Wrong login or password"
        })
    }

}