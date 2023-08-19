package com.newscheck.ui.onenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newscheck.model.News
import com.newscheck.repositories.OneNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneNewsViewModel @Inject constructor(
    private val oneNewsRepository: OneNewsRepository
) : ViewModel() {

    fun saveNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            oneNewsRepository.saveNews(news)
        }
    }

}