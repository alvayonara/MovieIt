package com.alvayonara.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.alvayonara.moviecatalogue.data.CatalogueDataSource
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.data.source.remote.RemoteDataSource

class FakeCatalogueRepository(private val remoteDataSource: RemoteDataSource) :
    CatalogueDataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> = remoteDataSource.getAllMovies()

    override fun getMovieById(movieId: String): LiveData<MovieEntity> =
        remoteDataSource.getMovieById(movieId)

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> = remoteDataSource.getAllTvShows()

    override fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> =
        remoteDataSource.getTvShowById(tvShowId)
}