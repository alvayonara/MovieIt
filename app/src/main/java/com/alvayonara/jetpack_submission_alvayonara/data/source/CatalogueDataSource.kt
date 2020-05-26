package com.alvayonara.jetpack_submission_alvayonara.data.source

import androidx.lifecycle.LiveData
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity

interface CatalogueDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>
}