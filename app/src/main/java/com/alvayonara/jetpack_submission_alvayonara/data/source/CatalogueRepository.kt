package com.alvayonara.jetpack_submission_alvayonara.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity
import com.alvayonara.jetpack_submission_alvayonara.data.source.remote.RemoteDataSource

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData)
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> = remoteDataSource.getAllMovies()

    override fun getMovieById(movieId: String): LiveData<MovieEntity> =
        remoteDataSource.getMovieById(movieId)

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> = remoteDataSource.getAllTvShows()

    override fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> =
        remoteDataSource.getTvShowById(tvShowId)
}