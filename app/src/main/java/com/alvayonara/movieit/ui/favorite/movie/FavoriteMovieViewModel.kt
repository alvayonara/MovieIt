package com.alvayonara.movieit.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alvayonara.movieit.data.CatalogueRepository
import com.alvayonara.movieit.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getFavoredMovies(): LiveData<PagedList<MovieEntity>> =
        catalogueRepository.getFavoredMovies()
}