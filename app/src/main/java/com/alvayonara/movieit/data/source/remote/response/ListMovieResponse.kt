package com.alvayonara.movieit.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(

    @SerializedName("results")
    val movies: List<MovieResponse>
)
