package com.alvayonara.moviecatalogue.api

import com.alvayonara.moviecatalogue.data.source.remote.response.MovieResponse
import com.alvayonara.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApi {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") api: String?,
        @Query("language") language: String?
    ): Call<MovieResponse>

    @GET("3/movie/{movieId}")
    fun getMovieById(
        @Path("movieId") movieId: String?,
        @Query("api_key") api: String?,
        @Query("language") language: String?
    ): Call<MovieResponse>

    @GET("3/discover/tv")
    fun getTvShows(
        @Query("api_key") api: String?,
        @Query("language") language: String?
    ): Call<TvShowResponse>

    @GET("3/tv/{tvShowId}")
    fun getTvShowById(
        @Path("tvShowId") tvShowId: String?,
        @Query("api_key") api: String?,
        @Query("language") language: String?
    ): Call<TvShowResponse>
}