package com.alvayonara.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.moviecatalogue.data.TvShowEntity
import com.alvayonara.moviecatalogue.data.source.CatalogueRepository

class DetailTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private lateinit var tvShowId: String

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): LiveData<TvShowEntity> = catalogueRepository.getTvShowById(tvShowId)
}