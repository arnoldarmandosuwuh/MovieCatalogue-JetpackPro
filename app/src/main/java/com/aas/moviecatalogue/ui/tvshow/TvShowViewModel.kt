package com.aas.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity

class TvShowViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getTvShow(): LiveData<List<TvShowEntity>> = mainRepository.getTvShows()

}