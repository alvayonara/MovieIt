package com.alvayonara.jetpack_submission_alvayonara.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.alvayonara.jetpack_submission_alvayonara.data.source.CatalogueRepository

class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): LiveData<MovieEntity> = catalogueRepository.getMovieById(movieId)
}