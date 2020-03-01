package com.alvayonara.jetpack_submission_alvayonara.ui.tvshow

import androidx.lifecycle.ViewModel
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity
import com.alvayonara.jetpack_submission_alvayonara.utils.DataDummy

class TvShowViewModel:ViewModel() {

    fun getTvShows() : List<TvShowEntity> = DataDummy.generateDummyTvShows()
}