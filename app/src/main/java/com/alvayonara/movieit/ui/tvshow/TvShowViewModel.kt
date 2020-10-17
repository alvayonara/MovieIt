package com.alvayonara.movieit.ui.tvshow

import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class TvShowViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val tvShows = catalogueUseCase.getAllTvShows()
}