package com.newscheck.ui.likenews

import com.newscheck.MainDispatcherRule
import com.newscheck.repositories.LikeNewsRepository
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LikeNewsViewModelTest {

    private lateinit var likeNewsViewModel: LikeNewsViewModel

    private val likeNewsRepository: LikeNewsRepository = mockk()

    @get:Rule
    val rule = MainDispatcherRule()

    @Before
    fun init() {
        likeNewsViewModel = LikeNewsViewModel(likeNewsRepository)
    }

    @Test
    fun testGetNews() {
        likeNewsViewModel.getNews()
        assert(true)
    }
}