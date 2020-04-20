package com.aas.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.remote.model.TvShowModel

class TvShowViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "This is bait"
    }

    val tvShow = Transformations.switchMap(bait) { mainRepository.getTvShows() }

}