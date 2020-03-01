package com.alvayonara.jetpack_submission_alvayonara.ui.movie

import com.alvayonara.jetpack_submission_alvayonara.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.movieId)
        val movieEntity = viewModel.getMovie()

        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.averageVote, movieEntity.averageVote)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.status, movieEntity.status)
        assertEquals(dummyMovie.originalTitle, movieEntity.originalTitle)
        assertEquals(dummyMovie.originalLanguage, movieEntity.originalLanguage)
        assertEquals(dummyMovie.runtime, movieEntity.runtime)
    }
}