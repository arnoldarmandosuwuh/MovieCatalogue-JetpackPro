package com.aas.moviecatalogue.data.source.remote.model

data class TvShowModel(
    val id: String,
    val name: String? = "",
    val overview: String? = "",
    val poster_path: String? = "",
    val first_air_date: String? = "",
    val vote_average: Double,
    val popularity: Double,
    val backdrop_path: String? = ""
)