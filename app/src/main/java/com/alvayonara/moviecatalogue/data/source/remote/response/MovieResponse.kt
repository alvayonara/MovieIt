package com.alvayonara.moviecatalogue.data.source.remote.response

import com.alvayonara.moviecatalogue.data.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movies: List<MovieEntity>
)