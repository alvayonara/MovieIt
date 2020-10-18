package com.alvayonara.movieit.data.source.remote

import android.annotation.SuppressLint
import com.alvayonara.movieit.BuildConfig
import com.alvayonara.movieit.data.source.remote.network.ApiResponse
import com.alvayonara.movieit.data.source.remote.network.ApiService
import com.alvayonara.movieit.data.source.remote.response.MovieResponse
import com.alvayonara.movieit.data.source.remote.response.TvShowResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {

        private const val LANGUAGE = "en-US"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    @SuppressLint("CheckResult")
    fun getAllMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val movieResults = PublishSubject.create<ApiResponse<List<MovieResponse>>>()

        val client = apiService.getMovies(BuildConfig.TMDB_API_KEY, LANGUAGE)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.movies
                movieResults.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                movieResults.onNext(ApiResponse.Error(error.message.toString()))
            })

        return movieResults.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getMovieById(movieId: String): Flowable<ApiResponse<MovieResponse>> {
        val movieResult = PublishSubject.create<ApiResponse<MovieResponse>>()

        val client = apiService.getMovieById(movieId, BuildConfig.TMDB_API_KEY, LANGUAGE)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                movieResult.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                movieResult.onNext(ApiResponse.Error(error.message.toString()))
            })

        return movieResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAllTvShows(): Flowable<ApiResponse<List<TvShowResponse>>> {
        val tvShowResults = PublishSubject.create<ApiResponse<List<TvShowResponse>>>()

        val client = apiService.getTvShows(BuildConfig.TMDB_API_KEY, LANGUAGE)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.tvShows
                tvShowResults.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                tvShowResults.onNext(ApiResponse.Error(error.message.toString()))
            })

        return tvShowResults.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getTvShowById(tvShowId: String): Flowable<ApiResponse<TvShowResponse>> {
        val tvShowResult = PublishSubject.create<ApiResponse<TvShowResponse>>()

        val client = apiService.getTvShowById(tvShowId, BuildConfig.TMDB_API_KEY, LANGUAGE)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                tvShowResult.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                tvShowResult.onNext(ApiResponse.Error(error.message.toString()))
            })

        return tvShowResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getMovieSearch(query: String): Flowable<ApiResponse<List<MovieResponse>>> {
        val movieResults = PublishSubject.create<ApiResponse<List<MovieResponse>>>()

        val client = apiService.getMovieSearch(BuildConfig.TMDB_API_KEY, LANGUAGE, query)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.movies
                movieResults.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                movieResults.onNext(ApiResponse.Error(error.message.toString()))
            })

        return movieResults.toFlowable(BackpressureStrategy.BUFFER)
    }
}