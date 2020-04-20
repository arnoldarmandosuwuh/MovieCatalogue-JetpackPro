package com.aas.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.source.MainRepository

class MovieFavoriteViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "This is bait"
    }

    val favoriteMovie = Transformations.switchMap(bait) { mainRepository.getMovieFavoritePaged() }
}