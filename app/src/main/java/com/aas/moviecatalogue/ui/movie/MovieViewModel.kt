package com.aas.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.MovieEntity
import com.aas.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovie() : MutableList<MovieEntity> = DataDummy.generateMovieData()

}