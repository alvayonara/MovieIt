package com.alvayonara.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getFavoredTvShows(): LiveData<PagedList<TvShowEntity>> =
        catalogueRepository.getFavoredTvShows()
}