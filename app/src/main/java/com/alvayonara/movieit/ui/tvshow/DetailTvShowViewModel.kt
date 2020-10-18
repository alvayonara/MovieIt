package com.alvayonara.movieit.ui.tvshow

import androidx.lifecycle.*
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
            LiveDataReactiveStreams.fromPublisher(catalogueUseCase.getTvShowById(it))
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