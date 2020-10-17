package com.alvayonara.movieit.domain.repository

import androidx.lifecycle.LiveData
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.domain.model.TvShow
import com.alvayonara.movieit.data.Resource

interface ICatalogueRepository {

    fun getAllMovies(): LiveData<Resource<List<Movie>>>

    fun getMovieById(movieId: String): LiveData<Resource<Movie>>

    fun getFavoredMovies(): LiveData<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)

    fun getAllTvShows(): LiveData<Resource<List<TvShow>>>

    fun getTvShowById(tvShowId: String): LiveData<Resource<TvShow>>

    fun getFavoredTvShows(): LiveData<List<TvShow>>

    fun setTvShowFavorite(tvShow: TvShow, state: Boolean)

    fun getMovieSearch(query: String): LiveData<Resource<List<Movie>>>
}