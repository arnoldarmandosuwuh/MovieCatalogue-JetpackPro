package com.aas.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.source.MainRepository

class TvShowFavoriteViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "This is bait"
    }

    val favoriteTvShow = Transformations.switchMap(bait) { mainRepository.getTvShowFavoritePaged() }
}