package com.newscheck.repositories

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val auth: FirebaseAuth
) {

    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onError(task.exception)
            }
        }
    }

}