package com.newscheck.ui.allnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newscheck.model.News
import com.newscheck.repositories.AllNewsRepository
import com.newscheck.repositories.NetworkStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllNewsViewModel @Inject constructor(
    private val newsRepository: AllNewsRepository,
    private val networkStatusRepository: NetworkStatusRepository
) : ViewModel() {

    var news = MutableLiveData<ArrayList<News>>()

    fun getNews(cat: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = newsRepository.getNews(cat)
            if (response.isSuccessful) {
                news.postValue(
                    response.body()?.data
                )
            }
        }
    }

    fun getNetworkState(): MutableStateFlow<Boolean> {
        return networkStatusRepository.getNetworkState()
    }

}