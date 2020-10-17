package com.alvayonara.movieit.ui.movie

import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class MovieViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val movies = catalogueUseCase.getAllMovies()
}