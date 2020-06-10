package com.alvayonara.movieit.di

import android.content.Context
import com.alvayonara.movieit.data.CatalogueRepository
import com.alvayonara.movieit.data.source.local.LocalDataSource
import com.alvayonara.movieit.data.source.local.room.CatalogueDatabase
import com.alvayonara.movieit.data.source.remote.RemoteDataSource
import com.alvayonara.movieit.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {

        val database = CatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}