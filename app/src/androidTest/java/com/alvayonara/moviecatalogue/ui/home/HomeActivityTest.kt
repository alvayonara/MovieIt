package com.alvayonara.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

//    @get:Rule
//    var activityRule = ActivityTestRule(HomeActivity::class.java)
//
//    @Before
//    fun setUp() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
//    }
//
//    @After
//    fun tearDown() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
//    }
//
//    @Test
//    fun loadMovies() {
//        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
//        onView(withText(R.string.tab_text_1)).perform(click())
//        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie)).perform(
//            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
//                10
//            )
//        )
//    }
//
//    @Test
//    fun loadDetailMovie() {
//        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
//        onView(withText(R.string.tab_text_1)).perform(click())
//        onView(withId(R.id.rv_movie)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//        onView(withId(R.id.title_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.vote_average_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.release_date_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.popularity_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.original_title_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.original_language_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.vote_count_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.poster_movie_detail)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun loadTvShows() {
//        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
//        onView(withText(R.string.tab_text_2)).perform(click())
//        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_tv_show)).perform(
//            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
//                10
//            )
//        )
//    }
//
//    @Test
//    fun loadDetailTvShow() {
//        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
//        onView(withText(R.string.tab_text_2)).perform(click())
//        onView(withId(R.id.rv_tv_show)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//        onView(withId(R.id.title_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.vote_average_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.release_date_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.popularity_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.original_title_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.original_language_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.vote_count_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.poster_tv_show_detail)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun loadFavoriteMovie() {
//        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
//        onView(withText(R.string.tab_text_1)).perform(click())
//        onView(withId(R.id.rv_movie)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//        onView(withId(R.id.action_favorite)).perform(click())
//        onView(isRoot()).perform(ViewActions.pressBack())
//        onView(withId(R.id.action_to_favorite)).perform(click())
//        onView(withId(R.id.tabs_favorite)).check(matches(isDisplayed()))
//        onView(withText(R.string.tab_text_1)).perform(click())
//        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//        onView(withId(R.id.title_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.vote_average_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.release_date_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.popularity_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.original_title_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.original_language_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.vote_count_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.poster_movie_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.action_favorite)).perform(click())
//        onView(isRoot()).perform(ViewActions.pressBack())
//    }
//
//    @Test
//    fun loadFavoriteTvShow() {
//        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
//        onView(withText(R.string.tab_text_2)).perform(click())
//        onView(withId(R.id.rv_tv_show)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//        onView(withId(R.id.action_favorite)).perform(click())
//        onView(isRoot()).perform(ViewActions.pressBack())
//        onView(withId(R.id.action_to_favorite)).perform(click())
//        onView(withId(R.id.tabs_favorite)).check(matches(isDisplayed()))
//        onView(withText(R.string.tab_text_2)).perform(click())
//        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_tv_show)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//        onView(withId(R.id.title_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.vote_average_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.release_date_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.popularity_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.original_title_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.original_language_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.vote_count_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.poster_tv_show_detail)).check(matches(isDisplayed()))
//        onView(withId(R.id.action_favorite)).perform(click())
//        onView(isRoot()).perform(ViewActions.pressBack())
//    }
}