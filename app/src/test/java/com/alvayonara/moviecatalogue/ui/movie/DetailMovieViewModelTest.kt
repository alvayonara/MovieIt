package com.alvayonara.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.utils.FakeDataDummy
import com.alvayonara.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = FakeDataDummy.generateRemoteDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(catalogueRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val dummyMovieDetail = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovieDetail

        `when`(catalogueRepository.getMovieById(movieId)).thenReturn(movie)

        viewModel.movieDetail.observeForever(movieObserver)

        verify(movieObserver).onChanged(dummyMovieDetail)
    }
}