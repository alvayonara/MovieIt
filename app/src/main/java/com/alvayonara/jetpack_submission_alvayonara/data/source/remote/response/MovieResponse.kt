package com.alvayonara.jetpack_submission_alvayonara.data.source.remote.response

import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movies: List<MovieEntity>
)