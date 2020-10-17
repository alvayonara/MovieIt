package com.alvayonara.movieit.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.model.TvShow
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase
import com.alvayonara.movieit.data.Resource

class DetailTvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val tvShowId = MutableLiveData<String>()

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId.value = tvShowId
    }

    var tvShowDetail: LiveData<Resource<TvShow>> =
        Transformations.switchMap(tvShowId) {
            catalogueUseCase.getTvShowById(it)
        }

    fun setFavoriteTvShow() {
        val tvShowDetailResource = tvShowDetail.value
        if (tvShowDetailResource != null) {
            val tvShow = tvShowDetailResource.data

            if (tvShow != null) {
                val newState = !tvShow.favored

                catalogueUseCase.setTvShowFavorite(tvShow, newState)
            }
        }
    }
}