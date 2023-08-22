package com.newscheck.ui.likenews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newscheck.model.News
import com.newscheck.repositories.LikeNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeNewsViewModel @Inject constructor(
    private val likeNewsRepository: LikeNewsRepository
) : ViewModel() {

    val newsList = MutableLiveData<ArrayList<News>>()

    private var email = ""

    fun getEmail() {
        email = likeNewsRepository.getEmail()
    }

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            newsList.postValue(likeNewsRepository.getNews(email))
        }
    }

}