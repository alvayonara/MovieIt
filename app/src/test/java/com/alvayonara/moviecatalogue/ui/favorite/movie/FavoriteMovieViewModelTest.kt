package com.alvayonara.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel =
            FavoriteMovieViewModel(
                catalogueRepository
            )
    }

    @Test
    fun getFavoredMovies() {
        val dummyFavoredMovies = pagedList
        `when`(dummyFavoredMovies.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
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