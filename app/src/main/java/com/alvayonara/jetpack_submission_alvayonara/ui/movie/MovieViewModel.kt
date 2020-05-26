package com.alvayonara.jetpack_submission_alvayonara.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.alvayonara.jetpack_submission_alvayonara.data.source.CatalogueRepository

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = catalogueRepository.getAllMovies()
}