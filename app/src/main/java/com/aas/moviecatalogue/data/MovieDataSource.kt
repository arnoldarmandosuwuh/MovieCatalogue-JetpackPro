package com.aas.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.aas.moviecatalogue.data.source.remote.response.DetailTvShowResponse

interface MovieDataSource {
    fun getMovies(): LiveData<List<MovieEntity>>
    fun getTvShows(): LiveData<List<TvShowEntity>>
    fun getDetailMovie(id: String): LiveData<DetailMovieResponse>
    fun getDetailTvShow(id: String): LiveData<DetailTvShowResponse>
}