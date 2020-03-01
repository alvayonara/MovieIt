package com.alvayonara.jetpack_submission_alvayonara.ui.tvshow

import androidx.lifecycle.ViewModel
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity
import com.alvayonara.jetpack_submission_alvayonara.utils.DataDummy

class DetailTvShowViewModel : ViewModel() {
    private lateinit var tvShowId: String

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowsEntities = DataDummy.generateDummyTvShows()
        for (tvShowEntity in tvShowsEntities) {
            if (tvShowEntity.tvShowId == tvShowId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}