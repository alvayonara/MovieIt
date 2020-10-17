package com.alvayonara.movieit.di

import android.content.Context
import com.alvayonara.movieit.data.CatalogueRepository
import com.alvayonara.movieit.data.source.local.LocalDataSource
import com.alvayonara.movieit.data.source.local.room.CatalogueDatabase
import com.alvayonara.movieit.data.source.remote.RemoteDataSource
import com.alvayonara.movieit.data.source.remote.network.ApiConfig
import com.alvayonara.movieit.domain.repository.ICatalogueRepository
import com.alvayonara.movieit.domain.usecase.CatalogueInteractor
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase
import com.alvayonara.movieit.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): ICatalogueRepository {

        val database = CatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideCatalogueUseCase(context: Context): CatalogueUseCase {
        val repository = provideRepository(context)
        return CatalogueInteractor(repository)
    }
}