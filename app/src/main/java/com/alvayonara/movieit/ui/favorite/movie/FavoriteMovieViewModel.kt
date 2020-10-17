package com.alvayonara.movieit.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class FavoriteMovieViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val favoriteMovies = catalogueUseCase.getFavoredMovies()
}