package com.newscheck.repositories

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignInRepository @Inject constructor(
    private val auth: FirebaseAuth
) {

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onError(task.exception)
            }
        }
    }

}