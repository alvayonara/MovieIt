package com.alvayonara.movieit.ui.movie

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class MovieViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val movies = LiveDataReactiveStreams.fromPublisher(catalogueUseCase.getAllMovies())
}