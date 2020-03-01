package com.alvayonara.jetpack_submission_alvayonara.ui.tvshow

import com.alvayonara.jetpack_submission_alvayonara.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.tvShowId

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(dummyTvShow.tvShowId)
        val tvShowEntity = viewModel.getTvShow()

        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.averageVote, tvShowEntity.averageVote)
        assertEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.status, tvShowEntity.status)
        assertEquals(dummyTvShow.originalTitle, tvShowEntity.originalTitle)
        assertEquals(dummyTvShow.originalLanguage, tvShowEntity.originalLanguage)
        assertEquals(dummyTvShow.runtime, tvShowEntity.runtime)
    }
}