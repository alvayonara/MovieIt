package com.alvayonara.movieit.data.source.local.room

import androidx.room.*
import com.alvayonara.movieit.data.source.local.entity.MovieEntity
import com.alvayonara.movieit.data.source.local.entity.TvShowEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movie WHERE search=0")
    fun getMovies(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE favored = 1")
    fun getFavoredMovie(): Flowable<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    fun getMovieById(movieId: String): Flowable<MovieEntity>

    @Query("SELECT * FROM movie WHERE title LIKE :title AND search=1")
    fun getMovieSearch(title: String): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>): Completable

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM tv_show")
    fun getTvShows(): Flowable<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show WHERE favored=1")
    fun getFavoredTvShow(): Flowable<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM tv_show WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: String): Flowable<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>): Completable

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}