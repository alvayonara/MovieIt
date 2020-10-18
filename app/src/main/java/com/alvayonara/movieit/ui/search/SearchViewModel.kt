package com.alvayonara.movieit.ui.search

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase

class SearchViewModel(private val catalogueUseCase: CatalogueUseCase): ViewModel() {

    fun getMovieSearch(query: String) = LiveDataReactiveStreams.fromPublisher(catalogueUseCase.getMovieSearch(query))
}