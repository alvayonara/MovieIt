package com.alvayonara.jetpack_submission_alvayonara.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var movieId: String? = null,
    var title: String? = null,
    var originalTitle: String? = null,
    var originalLanguage: String? = null,
    var posterPath: String? = null,
    var overview: String? = null,
    var releaseDate: String? = null,
    var voteCount: String? = null,
    var popularity: String? = null,
    var averageVote: String? = null
) : Parcelable