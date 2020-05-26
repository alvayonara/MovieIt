package com.alvayonara.jetpack_submission_alvayonara.api

import com.alvayonara.jetpack_submission_alvayonara.BuildConfig

object TheMovieDBApi {

    fun getMovies(): String =
        BuildConfig.BASE_URL_TMDB + "3/discover/movie?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US"

    fun getMovieById(movieId: String) =
        BuildConfig.BASE_URL_TMDB + "3/movie/${movieId}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US"

    fun getTvShows(): String =
        BuildConfig.BASE_URL_TMDB + "3/discover/tv?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US"

    fun getTvShowById(tvShowId: String) =
        BuildConfig.BASE_URL_TMDB + "3/tv/${tvShowId}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US"
}