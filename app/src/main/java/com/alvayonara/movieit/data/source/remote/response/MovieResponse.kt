package com.alvayonara.movieit.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(

    @SerializedName("id")
    var movieId: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("original_title")
    var originalTitle: String,

    @SerializedName("original_language")
    var originalLanguage: String,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var releaseDate: String,

    @SerializedName("vote_count")
    var voteCount: String,

    @SerializedName("popularity")
    var popularity: String,

    @SerializedName("vote_average")
    var averageVote: String
) : Parcelable