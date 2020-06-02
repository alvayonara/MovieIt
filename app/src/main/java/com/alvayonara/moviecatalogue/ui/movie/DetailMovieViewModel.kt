package com.alvayonara.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.moviecatalogue.data.MovieEntity
import com.alvayonara.moviecatalogue.data.source.CatalogueRepository

class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): LiveData<MovieEntity> = catalogueRepository.getMovieById(movieId)
}