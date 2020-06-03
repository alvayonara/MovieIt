package com.alvayonara.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getFavoredTvShows(): LiveData<List<TvShowEntity>> = catalogueRepository.getFavoredTvShows()
}