package com.alvayonara.movieit.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alvayonara.movieit.data.source.local.entity.MovieEntity
import com.alvayonara.movieit.data.source.local.entity.TvShowEntity
import com.alvayonara.movieit.vo.Resource

interface CatalogueDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>>

    fun getFavoredMovies(): LiveData<PagedList<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getTvShowById(tvShowId: String): LiveData<Resource<TvShowEntity>>

    fun getFavoredTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

    fun getMovieSearch(query: String): LiveData<Resource<List<MovieEntity>>>
}