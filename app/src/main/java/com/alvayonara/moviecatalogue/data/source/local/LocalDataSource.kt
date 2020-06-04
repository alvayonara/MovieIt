package com.alvayonara.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {

    companion object {

        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(catalogueDao)
            }
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getMovies()

    fun getFavoredMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getFavoredMovie()

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favored = newState
        mCatalogueDao.updateMovie(movie)
    }

    fun getMovieById(movieId: String): LiveData<MovieEntity> = mCatalogueDao.getMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) = mCatalogueDao.insertMovies(movies)

    fun updateMovie(movie: MovieEntity) = mCatalogueDao.updateMovie(movie)

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getTvShows()

    fun getFavoredTvShows(): DataSource.Factory<Int, TvShowEntity> =
        mCatalogueDao.getFavoredTvShow()

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favored = newState
        mCatalogueDao.updateTvShow(tvShow)
    }

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> =
        mCatalogueDao.getTvShowById(tvShowId)

    fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogueDao.insertTvShows(tvShows)

    fun updateTvShow(tvShow: TvShowEntity) = mCatalogueDao.updateTvShow(tvShow)
}