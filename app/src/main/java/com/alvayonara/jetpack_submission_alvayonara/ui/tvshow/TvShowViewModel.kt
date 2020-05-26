package com.alvayonara.jetpack_submission_alvayonara.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity
import com.alvayonara.jetpack_submission_alvayonara.data.source.CatalogueRepository

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = catalogueRepository.getAllTvShows()
}