package com.alvayonara.movieit.domain.repository

import com.alvayonara.movieit.data.Resource
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.domain.model.TvShow
import io.reactivex.Flowable

interface ICatalogueRepository {

    fun getAllMovies(): Flowable<Resource<List<Movie>>>

    fun getMovieById(movieId: String): Flowable<Resource<Movie>>

    fun getFavoredMovies(): Flowable<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)

    fun getAllTvShows(): Flowable<Resource<List<TvShow>>>

    fun getTvShowById(tvShowId: String): Flowable<Resource<TvShow>>

    fun getFavoredTvShows(): Flowable<List<TvShow>>

    fun setTvShowFavorite(tvShow: TvShow, state: Boolean)

    fun getMovieSearch(query: String): Flowable<Resource<List<Movie>>>
}