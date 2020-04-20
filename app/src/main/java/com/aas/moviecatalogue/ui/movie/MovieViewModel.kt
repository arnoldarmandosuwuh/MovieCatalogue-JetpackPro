package com.aas.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity

class MovieViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getMovie(): LiveData<List<MovieEntity>> = mainRepository.getMovies()

}