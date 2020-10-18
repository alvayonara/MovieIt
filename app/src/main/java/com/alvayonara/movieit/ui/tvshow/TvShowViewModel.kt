package com.alvayonara.movieit.ui.tvshow

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class TvShowViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val tvShows = LiveDataReactiveStreams.fromPublisher(catalogueUseCase.getAllTvShows())
}