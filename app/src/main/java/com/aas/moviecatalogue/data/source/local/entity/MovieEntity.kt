package com.aas.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aas.moviecatalogue.data.source.local.map.MovieMap
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = MovieMap.tableName)
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = MovieMap.id)
    var id: String,

    @NonNull
    @ColumnInfo(name = MovieMap.title)
    var title: String,

    @NonNull
    @ColumnInfo(name = MovieMap.overview)
    var overview: String,

    @NonNull
    @ColumnInfo(name = MovieMap.posterPath)
    var posterPath: String,

    @NonNull
    @ColumnInfo(name = MovieMap.favorite)
    var favorite: Boolean = false,

    @NonNull
    @ColumnInfo(name = MovieMap.releaseDate)
    var releaseDate: String,

    @NonNull
    @ColumnInfo(name = MovieMap.voteAverage)
    var voteAverage: Double,

    @NonNull
    @ColumnInfo(name = MovieMap.popularity)
    var popularity: Double,

    @NonNull
    @ColumnInfo(name = MovieMap.backdropPath)
    var backdropPath: String
) : Parcelable