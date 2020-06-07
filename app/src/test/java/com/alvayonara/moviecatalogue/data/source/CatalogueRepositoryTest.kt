package com.alvayonara.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alvayonara.moviecatalogue.data.source.local.LocalDataSource
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.remote.RemoteDataSource
import com.alvayonara.moviecatalogue.utils.AppExecutors
import com.alvayonara.moviecatalogue.utils.FakeDataDummy
import com.alvayonara.moviecatalogue.utils.LiveDataTestUtil
import com.alvayonara.moviecatalogue.utils.PagedListUtil
import com.alvayonara.moviecatalogue.vo.Resource
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
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val catalogRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val movieResponses = FakeDataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].movieId
    private val tvShowResponses = FakeDataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponses[0].tvShowId

    @Test
    fun getAllMovies() {
        val movieSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(movieSourceFactory)
        catalogRepository.getDiscoverMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.generateRemoteDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = FakeDataDummy.generateRemoteDummyMovies()[0]
        `when`(local.getMovieById(movieId)).thenReturn(dummyEntity)

        val movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieById(movieId))

        verify(local).getMovieById(movieId)
        assertNotNull(movieEntities.data)
        assertNotNull(movieEntities.data?.title)
        assertEquals(movieResponses[0].title, movieEntities.data?.title)
    }

    @Test
    fun getFavoredMovies() {
        val favoredMovieSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoredMovies()).thenReturn(favoredMovieSourceFactory)
        catalogRepository.getFavoredMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.generateRemoteDummyMovies()))
        verify(local).getFavoredMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val tvShowSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(tvShowSourceFactory)
        catalogRepository.getAllTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.generateRemoteDummyTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() {
        val dummyEntity = MutableLiveData<TvShowEntity>()
        dummyEntity.value = FakeDataDummy.generateRemoteDummyTvShows()[0]
        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyEntity)

        val tvShowEntities = LiveDataTestUtil.getValue(catalogRepository.getTvShowById(tvShowId))

        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowEntities.data)
        assertNotNull(tvShowEntities.data?.title)
        assertEquals(tvShowResponses[0].title, tvShowEntities.data?.title)
    }

    @Test
    fun getFavoredTvShows() {
        val favoredTvShowSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoredTvShows()).thenReturn(favoredTvShowSourceFactory)
        catalogRepository.getFavoredTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(FakeDataDummy.generateRemoteDummyTvShows()))
        verify(local).getFavoredTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}