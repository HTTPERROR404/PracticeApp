package com.example.pocpracticeapp

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pocpracticeapp.ui.datalist.DataListFragment
import com.example.pocpracticeapp.ui.datalist.DataListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DataListFragmentInstumentedTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var scenario: FragmentScenario<DataListFragment>
    private val viewModel: DataListViewModel = mockk(relaxed = true)

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp(){
        hiltRule.inject()
        scenario = launchFragmentInContainer()
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun testEventFragment() {
        val scenario = launchFragmentInContainer<DataListFragment>()
        onView(withId(R.id.posts_progress_bar)).check(matches(isDisplayed()))
        assert(true)
    }
    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Loading_State() {
        scenario.onFragment{
        }
        onView(withId(R.id.posts_progress_bar)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Empty_Actors_List_State() {
        //TODO
    }

    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Actors_List_State() {
        //TODO
    }


}