package com.alvayonara.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.vo.Resource

class HomeViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = catalogueRepository.getAllMovies()

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = catalogueRepository.getAllTvShows()
}