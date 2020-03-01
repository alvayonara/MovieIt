package com.alvayonara.jetpack_submission_alvayonara.ui.movie

import androidx.lifecycle.ViewModel
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.alvayonara.jetpack_submission_alvayonara.utils.DataDummy

class MovieViewModel: ViewModel() {

    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
}