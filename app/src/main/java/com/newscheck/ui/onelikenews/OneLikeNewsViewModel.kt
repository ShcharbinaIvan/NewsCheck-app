package com.newscheck.ui.onelikenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newscheck.model.News
import com.newscheck.repositories.LikeNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneLikeNewsViewModel @Inject constructor(
    private val likeNewsRepository: LikeNewsRepository
) : ViewModel() {

    fun deleteNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            likeNewsRepository.deleteNews(news)
        }
    }
}