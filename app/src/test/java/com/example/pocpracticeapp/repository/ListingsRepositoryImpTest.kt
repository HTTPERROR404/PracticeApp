package com.example.pocpracticeapp.repository

import com.example.pocpracticeapp.domain.model.ListingsRoot
import com.example.pocpracticeapp.domain.repository.ListingsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test


class ListingsRepositoryImpTest {

    @MockK
    lateinit var postsRepository: ListingsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun getPostsData() = runBlocking {
        val posts = mockk<ListingsRoot>()
        every { runBlocking { postsRepository.getPosts() } } returns (posts)

        val result = postsRepository.getPosts()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$posts] must be matches on each other!",
            result,
            CoreMatchers.`is`(posts)
        )
    }
}