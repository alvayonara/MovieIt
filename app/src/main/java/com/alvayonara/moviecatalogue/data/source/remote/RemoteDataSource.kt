package com.alvayonara.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvayonara.moviecatalogue.BuildConfig
import com.alvayonara.moviecatalogue.api.ApiRepository
import com.alvayonara.moviecatalogue.data.MovieEntity
import com.alvayonara.moviecatalogue.data.TvShowEntity
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

    fun getAllMovies(): LiveData<List<MovieEntity>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<List<MovieEntity>>()

        ApiRepository().theMovieDBApi.getMovies(BuildConfig.TMDB_API_KEY, LANGUAGE).enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("Movie Request Error: ", t.toString())
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                movieResults.postValue(response.body()?.movies)
            }
        })
        EspressoIdlingResource.decrement()

        return movieResults
    }

    fun getMovieById(movieId: String): LiveData<MovieEntity> {
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<MovieEntity>()

        ApiRepository().theMovieDBApi.getMovieById(movieId, BuildConfig.TMDB_API_KEY, LANGUAGE)
            .enqueue(object : Callback<MovieEntity> {
                override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                    Log.e("Movie Request Error: ", t.toString())
                }

                override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                    movieResult.postValue(response.body())
                }
            })
        EspressoIdlingResource.decrement()

        return movieResult
    }

    fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        EspressoIdlingResource.increment()
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()

        ApiRepository().theMovieDBApi.getTvShows(BuildConfig.TMDB_API_KEY, LANGUAGE)
            .enqueue(object : Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    Log.e("TvShow Request Error: ", t.toString())
                }

                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    tvShowResults.postValue(response.body()?.tvShows)
                }

            })
        EspressoIdlingResource.decrement()

        return tvShowResults
    }

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> {
        EspressoIdlingResource.increment()
        val tvShowResult = MutableLiveData<TvShowEntity>()

        ApiRepository().theMovieDBApi.getTvShowById(tvShowId, BuildConfig.TMDB_API_KEY, LANGUAGE)
            .enqueue(object : Callback<TvShowEntity> {
                override fun onFailure(call: Call<TvShowEntity>, t: Throwable) {
                    Log.e("TvShow Request Error: ", t.toString())
                }

                override fun onResponse(
                    call: Call<TvShowEntity>,
                    response: Response<TvShowEntity>
                ) {
                    tvShowResult.postValue(response.body())
                }

            })
        EspressoIdlingResource.decrement()

        return tvShowResult
    }
}