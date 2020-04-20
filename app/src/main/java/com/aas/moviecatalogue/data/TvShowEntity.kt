package com.aas.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity (
    val title: String,
    val overview: String,
    val poster: Int,
    val releaseDate: String,
    val runningTime: String,
    val distributedBy: String
) : Parcelable