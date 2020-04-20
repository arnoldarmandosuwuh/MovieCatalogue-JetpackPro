package com.aas.moviecatalogue.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.ui.detail.DetailViewModel
import com.aas.moviecatalogue.ui.movie.MovieViewModel
import com.aas.moviecatalogue.ui.tvshow.TvShowViewModel

class ViewModelFactory(private val mainRepository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        private lateinit var instance: ViewModelFactory
        fun getInstance(): ViewModelFactory {
            synchronized(ViewModelFactory::class.java) {
                instance = ViewModelFactory(Injection.provideRepository())
            }
            return instance
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            MovieViewModel::class.java -> {
                return MovieViewModel(mainRepository) as T
            }
            TvShowViewModel::class.java -> {
                return TvShowViewModel(mainRepository) as T
            }
            DetailViewModel::class.java -> {
                return DetailViewModel(mainRepository) as T
            }
        }

        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)


    }

}