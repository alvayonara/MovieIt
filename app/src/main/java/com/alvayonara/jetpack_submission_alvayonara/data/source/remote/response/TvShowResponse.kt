package com.alvayonara.jetpack_submission_alvayonara.data.source.remote.response

import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity
import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("results")
    val tvShows: List<TvShowEntity>
)