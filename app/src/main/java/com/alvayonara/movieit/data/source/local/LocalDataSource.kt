package com.alvayonara.movieit.data.source.local

import com.alvayonara.movieit.data.source.local.entity.MovieEntity
import com.alvayonara.movieit.data.source.local.entity.TvShowEntity
import com.alvayonara.movieit.data.source.local.room.CatalogueDao
import io.reactivex.Flowable

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {

    companion object {

        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(catalogueDao)
            }
    }

    fun getAllMovies(): Flowable<List<MovieEntity>> = mCatalogueDao.getMovies()

    fun getFavoredMovies(): Flowable<List<MovieEntity>> = mCatalogueDao.getFavoredMovie()

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favored = newState
        mCatalogueDao.updateMovie(movie)
    }

    fun getMovieById(movieId: String): Flowable<MovieEntity> = mCatalogueDao.getMovieById(movieId)

    fun getMovieSearch(query: String): Flowable<List<MovieEntity>> = mCatalogueDao.getMovieSearch(query)

    fun insertMovies(movies: List<MovieEntity>) = mCatalogueDao.insertMovies(movies)

    fun updateMovie(movie: MovieEntity) = mCatalogueDao.updateMovie(movie)

    fun getAllTvShows(): Flowable<List<TvShowEntity>> = mCatalogueDao.getTvShows()

    fun getFavoredTvShows(): Flowable<List<TvShowEntity>> =
        mCatalogueDao.getFavoredTvShow()

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favored = newState
        mCatalogueDao.updateTvShow(tvShow)
    }

    fun getTvShowById(tvShowId: String): Flowable<TvShowEntity> =
        mCatalogueDao.getTvShowById(tvShowId)

    fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogueDao.insertTvShows(tvShows)

    fun updateTvShow(tvShow: TvShowEntity) = mCatalogueDao.updateTvShow(tvShow)
}