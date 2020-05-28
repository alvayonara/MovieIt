package com.alvayonara.jetpack_submission_alvayonara.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    @SerializedName("id")
    var tvShowId: String? = null,

    @SerializedName("name")
    var title: String? = null,

    @SerializedName("original_name")
    var originalTitle: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("first_air_date")
    var releaseDate: String? = null,

    @SerializedName("vote_count")
    var voteCount: String? = null,

    @SerializedName("popularity")
    var popularity: String? = null,

    @SerializedName("vote_average")
    var averageVote: String? = null
) : Parcelable