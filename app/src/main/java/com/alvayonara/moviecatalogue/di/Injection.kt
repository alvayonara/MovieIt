package com.alvayonara.moviecatalogue.di

import com.alvayonara.moviecatalogue.data.source.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): CatalogueRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return CatalogueRepository.getInstance(remoteDataSource)
    }
}