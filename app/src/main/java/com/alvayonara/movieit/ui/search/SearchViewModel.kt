package com.alvayonara.movieit.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.data.CatalogueRepository
import com.alvayonara.movieit.data.source.local.entity.MovieEntity
import com.alvayonara.movieit.vo.Resource

class SearchViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {

    fun getMovieSearch(query: String): LiveData<Resource<List<MovieEntity>>> = catalogueRepository.getMovieSearch(query)
}