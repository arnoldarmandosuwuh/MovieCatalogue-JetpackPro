package com.aas.moviecatalogue.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var detailMovie = MutableLiveData<MovieEntity>()
    var detailTv = MutableLiveData<TvShowEntity>()

    fun setMovieState(movieEntity: MovieEntity, state: Boolean){
        mainRepository.setMovieState(movieEntity, state)
    }

    fun setTvShowState(tvShowEntity: TvShowEntity, state: Boolean){
        mainRepository.setTvShowState(tvShowEntity, state)
    }
}