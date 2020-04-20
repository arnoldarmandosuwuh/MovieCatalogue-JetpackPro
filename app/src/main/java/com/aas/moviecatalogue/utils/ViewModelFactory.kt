package com.aas.moviecatalogue.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.ui.detail.DetailViewModel
import com.aas.moviecatalogue.ui.favorite.movie.MovieFavoriteViewModel
import com.aas.moviecatalogue.ui.favorite.tvshow.TvShowFavoriteViewModel
import com.aas.moviecatalogue.ui.movie.MovieViewModel
import com.aas.moviecatalogue.ui.tvshow.TvShowFragment
import com.aas.moviecatalogue.ui.tvshow.TvShowViewModel

class ViewModelFactory(private val mainRepository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        private lateinit var instance: ViewModelFactory
        fun getInstance(application: Application): ViewModelFactory {
            synchronized(ViewModelFactory::class.java) {
                instance = ViewModelFactory(Injection.provideRepository(application))
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
            MovieFavoriteViewModel::class.java -> {
                return MovieFavoriteViewModel(mainRepository) as T
            }
            TvShowFavoriteViewModel::class.java -> {
                return TvShowFavoriteViewModel(mainRepository) as T
            }
        }

        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)


    }

}