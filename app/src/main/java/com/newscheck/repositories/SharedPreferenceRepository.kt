package com.newscheck.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val SHARED_PREFERENCE_FILE = "sharedPreferenceFile"
private const val FIRST_OPEN = "firstOpen"

class SharedPreferenceRepository @Inject constructor(
    @ApplicationContext context: Context
) {

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)
    }

    fun setFirstOpenApp(firstOpen: Boolean) {
        sharedPreferences.edit {
            putBoolean(FIRST_OPEN, firstOpen)
        }
    }

    fun firstOpenApp(): Boolean {
        return sharedPreferences.getBoolean(FIRST_OPEN, true)
    }

}