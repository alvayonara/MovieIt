package com.alvayonara.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.vo.Resource

class FavoriteMovieViewModel(private val catalogueRepository: CatalogueRepository):ViewModel() {

    fun getFavoredMovies(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavoredMovies()
}