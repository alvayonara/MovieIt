package com.alvayonara.jetpack_submission_alvayonara.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.alvayonara.jetpack_submission_alvayonara.DateConvert
import com.alvayonara.jetpack_submission_alvayonara.R
import com.alvayonara.jetpack_submission_alvayonara.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tab_text_1)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tab_text_1)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_movie_detail)).check(matches(withText(dummyMovie[0].title)))

        onView(withId(R.id.vote_average_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_average_movie_detail)).check(matches(withText(dummyMovie[0].averageVote)))

        onView(withId(R.id.release_date_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_movie_detail)).check(
            matches(
                withText(
                    DateConvert.convertDate(
                        dummyMovie[0].releaseDate
                    )
                )
            )
        )

        onView(withId(R.id.status_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.status_movie_detail)).check(matches(withText(dummyMovie[0].status)))

        onView(withId(R.id.original_title_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.original_title_movie_detail)).check(matches(withText(dummyMovie[0].originalTitle)))

        onView(withId(R.id.original_language_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.original_language_movie_detail)).check(matches(withText(dummyMovie[0].originalLanguage)))

        onView(withId(R.id.runtime_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.runtime_movie_detail)).check(matches(withText("${dummyMovie[0].runtime}m")))

        onView(withId(R.id.poster_movie_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShow(){
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title_tv_show_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_tv_show_detail)).check(matches(withText(dummyTvShow[0].title)))

        onView(withId(R.id.vote_average_tv_show_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_average_tv_show_detail)).check(matches(withText(dummyTvShow[0].averageVote)))

        onView(withId(R.id.release_date_tv_show_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_tv_show_detail)).check(
            matches(
                withText(
                    DateConvert.convertDate(
                        dummyTvShow[0].releaseDate
                    )
                )
            )
        )

        onView(withId(R.id.status_tv_show_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.status_tv_show_detail)).check(matches(withText(dummyTvShow[0].status)))

        onView(withId(R.id.original_title_tv_show_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.original_title_tv_show_detail)).check(matches(withText(dummyTvShow[0].originalTitle)))

        onView(withId(R.id.original_language_tv_show_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.original_language_tv_show_detail)).check(matches(withText(dummyTvShow[0].originalLanguage)))

        onView(withId(R.id.runtime_tv_show_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.runtime_tv_show_detail)).check(matches(withText("${dummyTvShow[0].runtime}m")))

        onView(withId(R.id.poster_tv_show_detail)).check(matches(isDisplayed()))
    }
}