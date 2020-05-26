package com.alvayonara.jetpack_submission_alvayonara.di

import com.alvayonara.jetpack_submission_alvayonara.data.source.CatalogueRepository
import com.alvayonara.jetpack_submission_alvayonara.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): CatalogueRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return CatalogueRepository.getInstance(remoteDataSource)
    }
}