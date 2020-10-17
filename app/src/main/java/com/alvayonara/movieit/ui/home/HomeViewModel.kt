package com.alvayonara.movieit.ui.home

import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class HomeViewModel(catalogueUseCase: CatalogueUseCase): ViewModel() {

    val movies = catalogueUseCase.getAllMovies()

    val tvShows = catalogueUseCase.getAllTvShows()
}