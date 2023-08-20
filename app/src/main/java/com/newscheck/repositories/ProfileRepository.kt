package com.newscheck.repositories

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class ProfileRepository @Inject constructor(
    private val auth: FirebaseAuth
) {

    fun logOut() {
        auth.signOut()
    }

    fun getEmail(): String {
        return auth.currentUser?.email.toString()
    }

    fun isUserLogin() = auth.currentUser != null

}