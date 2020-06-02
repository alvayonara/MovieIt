package com.alvayonara.moviecatalogue.data.source.remote.response

import com.alvayonara.moviecatalogue.data.TvShowEntity
import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("results")
    val tvShows: List<TvShowEntity>
)