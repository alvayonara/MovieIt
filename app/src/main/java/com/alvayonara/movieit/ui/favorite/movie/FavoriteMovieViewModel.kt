package com.alvayonara.movieit.ui.favorite.movie

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class FavoriteMovieViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val favoriteMovies = LiveDataReactiveStreams.fromPublisher(catalogueUseCase.getFavoredMovies())
}