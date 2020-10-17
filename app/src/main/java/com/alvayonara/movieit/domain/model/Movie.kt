package com.alvayonara.movieit.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val movieId: String,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val voteCount: String,
    val popularity: String,
    val averageVote: String,
    val favored: Boolean,
    val search: Boolean
) : Parcelable