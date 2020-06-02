package com.alvayonara.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.alvayonara.moviecatalogue.data.MovieEntity
import com.alvayonara.moviecatalogue.data.TvShowEntity

interface CatalogueDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>
}