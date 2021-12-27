package com.example.pocpracticeapp.domain.usecase

import com.example.pocpracticeapp.domain.model.ListingsRoot
import com.example.pocpracticeapp.domain.repository.ListingsRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class GetListingsUseCaseTest {

    @MockK
    lateinit var postsRepository: ListingsRepository

    @MockK
    lateinit var listingsRoot: ListingsRoot

    private val useCase by lazy { GetListingsUseCase(postsRepository) }


    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetListingsSuccess(){
//        runBlocking {
//            `when`(postsRepository.getPosts()).thenReturn(listingsRoot)
//            useCase.invoke(Any(), Any(), Any())
//                .test()
//                .assertComplete()
//        }
    }
}