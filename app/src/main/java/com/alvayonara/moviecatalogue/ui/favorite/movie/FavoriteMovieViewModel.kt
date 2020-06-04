package com.alvayonara.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val catalogueRepository: CatalogueRepository):ViewModel() {

    fun getFavoredMovies(): LiveData<List<MovieEntity>> = catalogueRepository.getFavoredMovies()
}