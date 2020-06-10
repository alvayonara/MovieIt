package com.alvayonara.movieit.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.data.CatalogueRepository
import com.alvayonara.movieit.data.source.local.entity.TvShowEntity
import com.alvayonara.movieit.vo.Resource

class DetailTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    val tvShowId = MutableLiveData<String>()

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId.value = tvShowId
    }

    var tvShowDetail: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            catalogueRepository.getTvShowById(mTvShowId)
        }

    fun setFavoriteTvShow() {
        val tvShowDetailResource = tvShowDetail.value
        if (tvShowDetailResource != null) {
            val tvShowEntity = tvShowDetailResource.data

            if (tvShowEntity != null) {
                val newState = !tvShowEntity.favored

                catalogueRepository.setTvShowFavorite(tvShowEntity, newState)
            }
        }
    }
}