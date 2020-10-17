package com.alvayonara.movieit.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse (

    @SerializedName("results")
    val tvShows: List<TvShowResponse>
)