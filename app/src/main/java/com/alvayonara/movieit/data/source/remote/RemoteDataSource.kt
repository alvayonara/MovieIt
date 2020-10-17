package com.alvayonara.movieit.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvayonara.movieit.BuildConfig
import com.alvayonara.movieit.data.source.remote.network.ApiResponse
import com.alvayonara.movieit.data.source.remote.network.ApiService
import com.alvayonara.movieit.data.source.remote.response.*
import com.alvayonara.movieit.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        val client = apiService.getMovies(BuildConfig.TMDB_API_KEY, LANGUAGE)

        client.enqueue(object :
            Callback<ListMovieResponse> {
            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                movieResults.value = ApiResponse.Error(t.message.toString())
            }

            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                val dataArray = response.body()?.movies
                movieResults.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
        })
        EspressoIdlingResource.decrement()

        return movieResults
    }

    fun getMovieById(movieId: String): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<ApiResponse<MovieResponse>>()

        val client = apiService.getMovieById(movieId, BuildConfig.TMDB_API_KEY, LANGUAGE)

        client.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                movieResult.value = ApiResponse.Error(t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val data = response.body()
                movieResult.value =
                    if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
            }
        })
        EspressoIdlingResource.decrement()

        return movieResult
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val tvShowResults = MutableLiveData<ApiResponse<List<TvShowResponse>>>()

        val client = apiService.getTvShows(BuildConfig.TMDB_API_KEY, LANGUAGE)

        client
            .enqueue(object : Callback<ListTvShowResponse> {
                override fun onFailure(call: Call<ListTvShowResponse>, t: Throwable) {
                    tvShowResults.value = ApiResponse.Error(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ListTvShowResponse>,
                    response: Response<ListTvShowResponse>
                ) {
                    val dataArray = response.body()?.tvShows
                    tvShowResults.value =
                        if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
                }

            })
        EspressoIdlingResource.decrement()

        return tvShowResults
    }

    fun getTvShowById(tvShowId: String): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val tvShowResult = MutableLiveData<ApiResponse<TvShowResponse>>()

        val client = apiService.getTvShowById(tvShowId, BuildConfig.TMDB_API_KEY, LANGUAGE)

        client
            .enqueue(object : Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    tvShowResult.value = ApiResponse.Error(t.message.toString())
                }

                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    val data = response.body()
                    tvShowResult.value =
                        if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
                }

            })
        EspressoIdlingResource.decrement()

        return tvShowResult
    }

    fun getMovieSearch(query: String): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        val client = apiService.getMovieSearch(BuildConfig.TMDB_API_KEY, LANGUAGE, query)

        client
            .enqueue(object : Callback<ListMovieResponse> {
                override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                    movieResults.value = ApiResponse.Error(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ListMovieResponse>,
                    response: Response<ListMovieResponse>
                ) {
                    val dataArray = response.body()?.movies
                    movieResults.value =
                        if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
                }
            })

        EspressoIdlingResource.decrement()

        return movieResults
    }
}