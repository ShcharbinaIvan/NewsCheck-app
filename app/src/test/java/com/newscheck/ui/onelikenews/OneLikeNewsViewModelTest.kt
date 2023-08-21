package com.newscheck.ui.onelikenews

import com.newscheck.MainDispatcherRule
import com.newscheck.model.News
import com.newscheck.repositories.LikeNewsRepository
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OneLikeNewsViewModelTest {

    private lateinit var oneLikeNewsViewModel: OneLikeNewsViewModel

    private val likeNewsRepository: LikeNewsRepository = mockk()

    private val news = News(1, "", "", "", "", "", "", "", "")

    @get:Rule
    val rule = MainDispatcherRule()

    @Before
    fun init() {
        oneLikeNewsViewModel = OneLikeNewsViewModel(likeNewsRepository)
    }

    @Test
    fun testDeleteNews() {
        oneLikeNewsViewModel.deleteNews(news)
        assert(true)
    }

}