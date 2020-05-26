package com.alvayonara.jetpack_submission_alvayonara.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvayonara.jetpack_submission_alvayonara.BuildConfig
import com.alvayonara.jetpack_submission_alvayonara.api.TheMovieDBApi
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONObject

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()

        val movieList = ArrayList<MovieEntity>()

        AndroidNetworking.get(TheMovieDBApi.getMovies())
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {

                    try {
                        val listArray = response.getJSONArray("results")

                        Log.d("array: ", listArray.toString())

                        for (i in 0 until listArray.length()) {
                            val movie = listArray.getJSONObject(i)

                            val id = movie.getString("id")
                            val title = movie.getString("title")
                            val originalTitle = movie.getString("original_title")
                            val originalLanguage = movie.getString("original_language")
                            val posterPath =
                                BuildConfig.BASE_URL_TMDB_POSTER + movie.getString("poster_path")
                            val overview = movie.getString("overview")
                            val releaseDate = movie.getString("release_date")
                            val voteCount = movie.getString("vote_count")
                            val popularity = movie.getString("popularity")
                            val averageVote = movie.getString("vote_average")

                            Log.d("average: ", averageVote)

                            val movieEntity = MovieEntity(
                                id,
                                title,
                                originalTitle,
                                originalLanguage,
                                posterPath,
                                overview,
                                releaseDate,
                                voteCount,
                                popularity,
                                averageVote
                            )
                            movieList.add(movieEntity)
                        }
                        movieResults.postValue(movieList)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("Movie Request Error: ", anError.toString())
                }
            })

        Log.d("list return: ", movieResults.toString())
        return movieResults
    }

    fun getMovieById(movieId: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()

        AndroidNetworking.get(TheMovieDBApi.getMovieById(movieId))
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {

                    try {
                        val id = response.getString("id")
                        val title = response.getString("title")
                        val originalTitle = response.getString("original_title")
                        val originalLanguage = response.getString("original_language")
                        val posterPath =
                            BuildConfig.BASE_URL_TMDB_POSTER + response.getString("poster_path")
                        val overview = response.getString("overview")
                        val releaseDate = response.getString("release_date")
                        val voteCount = response.getString("vote_count")
                        val popularity = response.getString("popularity")
                        val averageVote = response.getString("vote_average")

                        Log.d("average mov: ", averageVote)

                        val movieEntity = MovieEntity(
                            id,
                            title,
                            originalTitle,
                            originalLanguage,
                            posterPath,
                            overview,
                            releaseDate,
                            voteCount,
                            popularity,
                            averageVote
                        )
                        movieResult.postValue(movieEntity)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("Movie Request Error: ", anError.toString())
                }
            })

        return movieResult
    }

    fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()

        val tvShowList = ArrayList<TvShowEntity>()

        AndroidNetworking.get(TheMovieDBApi.getTvShows())
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val listArray = response.getJSONArray("results")

                        for (i in 0 until listArray.length()) {
                            val tvShow = listArray.getJSONObject(i)

                            val id = tvShow.getString("id")
                            val title = tvShow.getString("name")
                            val originalTitle = tvShow.getString("original_name")
                            val originalLanguage = tvShow.getString("original_language")
                            val posterPath =
                                BuildConfig.BASE_URL_TMDB_POSTER + tvShow.getString("poster_path")
                            val overview = tvShow.getString("overview")
                            val releaseDate = tvShow.getString("first_air_date")
                            val voteCount = tvShow.getString("vote_count")
                            val popularity = tvShow.getString("popularity")
                            val averageVote = tvShow.getString("vote_average")

                            val tvShowEntity = TvShowEntity(
                                id,
                                title,
                                originalTitle,
                                originalLanguage,
                                posterPath,
                                overview,
                                releaseDate,
                                voteCount,
                                popularity,
                                averageVote
                            )
                            tvShowList.add(tvShowEntity)
                        }
                        tvShowResults.postValue(tvShowList)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("Tv Show Request Error: ", anError.toString())
                }
            })

        return tvShowResults
    }

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()

        AndroidNetworking.get(TheMovieDBApi.getTvShowById(tvShowId))
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {

                    try {
                        val id = response.getString("id")
                        val title = response.getString("name")
                        val originalTitle = response.getString("original_name")
                        val originalLanguage = response.getString("original_language")
                        val posterPath =
                            BuildConfig.BASE_URL_TMDB_POSTER + response.getString("poster_path")
                        val overview = response.getString("overview")
                        val releaseDate = response.getString("first_air_date")
                        val voteCount = response.getString("vote_count")
                        val popularity = response.getString("popularity")
                        val averageVote = response.getString("vote_average")

                        val tvShowEntity = TvShowEntity(
                            id,
                            title,
                            originalTitle,
                            originalLanguage,
                            posterPath,
                            overview,
                            releaseDate,
                            voteCount,
                            popularity,
                            averageVote
                        )
                        tvShowResult.postValue(tvShowEntity)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("Tv Show Request Error: ", anError.toString())
                }
            })

        return tvShowResult
    }
}