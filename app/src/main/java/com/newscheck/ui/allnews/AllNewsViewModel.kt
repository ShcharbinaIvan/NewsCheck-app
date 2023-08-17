package com.newscheck.ui.allnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newscheck.model.News
import com.newscheck.repositories.AllNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllNewsViewModel @Inject constructor(
    private val newsRepository: AllNewsRepository
) : ViewModel() {

    var news = MutableLiveData<ArrayList<News>>()
    var oneNews=MutableLiveData<News>()

    fun getNews(cat:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = newsRepository.getNews(cat)
            if (response.isSuccessful) {
                news.postValue(
                    response.body()?.data
                )
            }
        }
    }

}