package com.alvayonara.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.data.source.remote.RemoteDataSource
import com.alvayonara.moviecatalogue.utils.FakeDataDummy
import com.alvayonara.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogRepository = FakeCatalogueRepository(remote)

    private val movieResponses = FakeDataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].movieId
    private val tvShowResponses = FakeDataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponses[0].tvShowId

    @Test
    fun getAllMovies() {
        val dummyMovies = FakeDataDummy.generateRemoteDummyMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(remote.getAllMovies()).thenReturn(movies)
        val movieEntities = LiveDataTestUtil.getValue(catalogRepository.getAllMovies())

        verify(remote).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = FakeDataDummy.generateRemoteDummyMovies()[0]
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(remote.getMovieById(movieId!!)).thenReturn(movie)
        val movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieById(movieId))

        verify(remote).getMovieById(movieId)
        assertNotNull(movieEntities)
        assertNotNull(movieEntities.title)
        assertEquals(movieResponses[0].title, movieEntities.title)
    }

    @Test
    fun getAllTvShows() {
        val dummyTvShows = FakeDataDummy.generateRemoteDummyTvShows()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(remote.getAllTvShows()).thenReturn(tvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(catalogRepository.getAllTvShows())

        verify(remote).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = FakeDataDummy.generateRemoteDummyTvShows()[0]
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        `when`(remote.getTvShowById(tvShowId!!)).thenReturn(tvShow)
        val tvShowEntities = LiveDataTestUtil.getValue(catalogRepository.getTvShowById(tvShowId))

        verify(remote).getTvShowById(tvShowId)
        assertNotNull(tvShowEntities)
        assertNotNull(tvShowEntities.title)
        assertEquals(tvShowResponses[0].title, tvShowEntities.title)
    }
}