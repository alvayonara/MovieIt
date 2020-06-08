package com.alvayonara.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvayonara.moviecatalogue.BuildConfig
import com.alvayonara.moviecatalogue.api.ApiRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.remote.response.MovieResponse
import com.alvayonara.moviecatalogue.data.source.remote.response.TvShowResponse
import com.alvayonara.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        private const val LANGUAGE = "en-US"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        ApiRepository().theMovieDBApi.getMovies(BuildConfig.TMDB_API_KEY, LANGUAGE).enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("Movie Request Error: ", t.toString())
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                movieResults.postValue(ApiResponse.success(response.body()!!.movies))
            }
        })
        EspressoIdlingResource.decrement()

        return movieResults
    }

    fun getMovieById(movieId: String): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<ApiResponse<MovieResponse>>()

        ApiRepository().theMovieDBApi.getMovieById(movieId, BuildConfig.TMDB_API_KEY, LANGUAGE)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("Movie Request Error: ", t.toString())
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    movieResult.postValue(ApiResponse.success(response.body()!!))
                }
            })
        EspressoIdlingResource.decrement()

        return movieResult
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val tvShowResults = MutableLiveData<ApiResponse<List<TvShowResponse>>>()

        ApiRepository().theMovieDBApi.getTvShows(BuildConfig.TMDB_API_KEY, LANGUAGE)
            .enqueue(object : Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    Log.e("TvShow Request Error: ", t.toString())
                }

                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    tvShowResults.postValue(ApiResponse.success(response.body()!!.tvShows))
                }

            })
        EspressoIdlingResource.decrement()

        return tvShowResults
    }

    fun getTvShowById(tvShowId: String): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val tvShowResult = MutableLiveData<ApiResponse<TvShowResponse>>()

        ApiRepository().theMovieDBApi.getTvShowById(tvShowId, BuildConfig.TMDB_API_KEY, LANGUAGE)
            .enqueue(object : Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    Log.e("TvShow Request Error: ", t.toString())
                }

                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    tvShowResult.postValue(ApiResponse.success(response.body()!!))
                }

            })
        EspressoIdlingResource.decrement()

        return tvShowResult
    }

    fun getMovieSearch(query: String): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        ApiRepository().theMovieDBApi.getMovieSearch(BuildConfig.TMDB_API_KEY, LANGUAGE, query)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("Movie Request Error: ", t.toString())
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    movieResults.postValue(ApiResponse.success(response.body()!!.movies))
                }
            })

        EspressoIdlingResource.decrement()

        return movieResults
    }
}