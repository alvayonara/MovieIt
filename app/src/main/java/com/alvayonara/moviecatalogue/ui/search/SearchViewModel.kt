package com.alvayonara.moviecatalogue.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.vo.Resource

class SearchViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {

    fun getMovieSearch(query: String): LiveData<Resource<PagedList<MovieEntity>>> = catalogueRepository.getMovieSearch(query)
}