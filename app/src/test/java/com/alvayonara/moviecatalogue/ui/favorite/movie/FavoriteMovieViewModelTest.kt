package com.alvayonara.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.utils.FakeDataDummy
import com.alvayonara.moviecatalogue.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp(){
        viewModel = FavoriteMovieViewModel(catalogueRepository)
    }

    @Test
    fun getFavoredMovies() {
        val dummyFavoredMovies = FakeDataDummy.generateRemoteDummyMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyFavoredMovies

        `when`(catalogueRepository.getFavoredMovies()).thenReturn(movies)
        val movieEntities = viewModel.getFavoredMovies().value
        verify(catalogueRepository).getFavoredMovies()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities?.size)

        viewModel.getFavoredMovies().observeForever(observer)
        verify(observer).onChanged(dummyFavoredMovies)
    }
}