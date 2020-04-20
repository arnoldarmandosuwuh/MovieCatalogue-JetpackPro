package com.aas.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aas.moviecatalogue.data.source.local.map.TvShowMap
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TvShowMap.tableName)
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = TvShowMap.id)
    var id: String,

    @NonNull
    @ColumnInfo(name = TvShowMap.name)
    var name: String,

    @NonNull
    @ColumnInfo(name = TvShowMap.overview)
    var overview: String,

    @NonNull
    @ColumnInfo(name = TvShowMap.posterPath)
    var posterPath: String,

    @NonNull
    @ColumnInfo(name = TvShowMap.favorite)
    var favorite: Boolean = false,

    @NonNull
    @ColumnInfo(name = TvShowMap.firstAirDate)
    var firstAirDate: String,

    @NonNull
    @ColumnInfo(name = TvShowMap.voteAverage)
    var voteAverage: Double,

    @NonNull
    @ColumnInfo(name = TvShowMap.popularity)
    var popularity: Double,

    @NonNull
    @ColumnInfo(name = TvShowMap.backdropPath)
    var backdropPath: String
) : Parcelable