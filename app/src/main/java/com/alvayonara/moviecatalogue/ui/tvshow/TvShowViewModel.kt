package com.alvayonara.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.moviecatalogue.data.TvShowEntity
import com.alvayonara.moviecatalogue.data.source.CatalogueRepository

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = catalogueRepository.getAllTvShows()
}