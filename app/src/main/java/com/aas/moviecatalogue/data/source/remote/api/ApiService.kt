package com.aas.moviecatalogue.data.source.remote.api

import com.aas.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.aas.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.aas.moviecatalogue.data.source.remote.response.MovieResponse
import com.aas.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun loadMovies(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>

    @GET("discover/tv")
    fun loadTvShows(
        @Query("api_key") api_key: String
    ): Call<TvShowResponse>

    @GET("movie/{id}")
    fun loadMovieDetail(
        @Path("id") id: String,
        @Query("api_key") api_key: String
    ): Call<DetailMovieResponse>

    @GET("tv/{id}")
    fun loadTVShowDetail(
        @Path("id") id: String,
        @Query("api_key") api_key: String
    ): Call<DetailTvShowResponse>
}