package com.alvayonara.movieit.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class HomeViewModel(catalogueUseCase: CatalogueUseCase): ViewModel() {

    val movies = LiveDataReactiveStreams.fromPublisher(catalogueUseCase.getAllMovies())

    val tvShows = LiveDataReactiveStreams.fromPublisher(catalogueUseCase.getAllTvShows())
}