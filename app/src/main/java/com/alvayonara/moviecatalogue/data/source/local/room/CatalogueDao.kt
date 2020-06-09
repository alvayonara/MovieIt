package com.alvayonara.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movie WHERE search=0")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE favored=1")
    fun getFavoredMovie(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    fun getMovieById(movieId: String): LiveData<MovieEntity>

    @Query("SELECT * FROM movie WHERE title LIKE :title AND search=1")
    fun getMovieSearch(title: String): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM tv_show")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show WHERE favored=1")
    fun getFavoredTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM tv_show WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}