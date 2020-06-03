package com.alvayonara.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.vo.Resource

interface CatalogueDataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>>

    fun getFavoredMovies(): LiveData<List<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getTvShowById(tvShowId: String): LiveData<Resource<TvShowEntity>>

    fun getFavoredTvShows(): LiveData<List<TvShowEntity>>

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)
}