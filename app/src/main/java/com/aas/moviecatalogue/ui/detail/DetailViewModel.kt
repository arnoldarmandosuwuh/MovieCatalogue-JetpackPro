package com.aas.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.aas.moviecatalogue.data.source.remote.response.DetailTvShowResponse

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getDetailMovie(id: String): LiveData<DetailMovieResponse> =
        mainRepository.getDetailMovie(id)

    fun getDetailTvShow(id: String): LiveData<DetailTvShowResponse> =
        mainRepository.getDetailTvShow(id)

}