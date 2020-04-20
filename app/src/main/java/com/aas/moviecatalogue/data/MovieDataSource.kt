package com.aas.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.vo.Resource

interface MovieDataSource {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>>
    fun getMovieFavoritePaged(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTvShowFavoritePaged(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun setMovieState(movieEntity: MovieEntity, state: Boolean)
    fun setTvShowState(tvShowEntity: TvShowEntity, state: Boolean)
}