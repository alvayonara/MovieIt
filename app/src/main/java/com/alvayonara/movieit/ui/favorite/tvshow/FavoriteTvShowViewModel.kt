package com.alvayonara.movieit.ui.favorite.tvshow

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class FavoriteTvShowViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val favoriteTvShow = LiveDataReactiveStreams.fromPublisher(catalogueUseCase.getFavoredTvShows())
}