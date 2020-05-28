package com.alvayonara.jetpack_submission_alvayonara.api

import android.app.Application
import com.alvayonara.jetpack_submission_alvayonara.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository : Application() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_TMDB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val theMovieDBApi: TheMovieDBApi = retrofit.create(TheMovieDBApi::class.java)
}