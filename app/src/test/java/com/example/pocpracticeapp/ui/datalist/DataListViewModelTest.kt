package com.example.pocpracticeapp.ui.datalist

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pocpracticeapp.CoroutinesTestRule
import com.example.pocpracticeapp.domain.model.ListingsRoot
import com.example.pocpracticeapp.domain.repository.ListingsRepository
import com.example.pocpracticeapp.domain.usecase.GetListingsUseCase
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DataListViewModelTest {

    @Mock
    private lateinit var repository: ListingsRepository

    private lateinit var viewModel: DataListViewModel

    private lateinit var useCase: GetListingsUseCase

    @Mock
    private lateinit var viewStateObserver: Observer<ListingsRoot>

    @Mock
    private lateinit var listingsRoot: ListingsRoot

    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetListingsUseCase(repository)
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        viewModel = DataListViewModel(useCase).apply {
            postsData.observeForever(viewStateObserver)
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getPostsDataSuccess() {
        testDispatcher.pauseDispatcher()
        runBlockingTest {
            `when`(repository.getPosts()).thenReturn(listingsRoot)
            viewModel.getPosts()
            testDispatcher.resumeDispatcher()

            testDispatcher.resumeDispatcher()
            assert(viewModel.postsData.value == listingsRoot)
        }
    }

    @Test
    fun getShowProgressbar() {
        testDispatcher.pauseDispatcher()
//        assert(viewModel.showProgressbar.value == true)
        runBlockingTest {
            `when`(repository.getPosts()).thenReturn(listingsRoot)
            viewModel.getPosts()
            testDispatcher.resumeDispatcher()
            assert(viewModel.postsData.value == listingsRoot)
            assert(viewModel.showProgressbar.value == false)
        }
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
}