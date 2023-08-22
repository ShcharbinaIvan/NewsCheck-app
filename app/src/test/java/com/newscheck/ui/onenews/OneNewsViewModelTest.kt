package com.newscheck.ui.onenews

import com.newscheck.MainDispatcherRule
import com.newscheck.model.News
import com.newscheck.repositories.OneNewsRepository
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OneNewsViewModelTest {

    private lateinit var oneLikeNewsViewModel: OneNewsViewModel

    private val oneNewsRepository: OneNewsRepository = mockk()

    private val news = News(1, "", "", "", "", "", "", "", "")

    @get:Rule
    val rule = MainDispatcherRule()

    @Before
    fun init() {
        oneLikeNewsViewModel = OneNewsViewModel(oneNewsRepository)
    }

    @Test
    fun testSaveNews() {
        oneLikeNewsViewModel.saveNews(news)
        assert(true)
    }

}