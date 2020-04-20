package com.aas.moviecatalogue.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.local.room.MovieDao
import com.aas.moviecatalogue.data.source.local.room.MovieDatabase
import java.util.concurrent.Executors

open class LocalRepository(context: Context) {

    private val movieDao: MovieDao = MovieDatabase.getDatabase(context).movieDao()

    private var executorService = Executors.newSingleThreadExecutor()

    open fun getMovie(): LiveData<List<MovieEntity>> {
        return movieDao.getFavoriteMovie()
    }

    open fun getFavoriteMoviePaged(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getFavoriteMovieAsPaged()
    }

    open fun getTvShow(): LiveData<List<TvShowEntity>> {
        return movieDao.getFavoriteTvShow()
    }

    open fun getFavoriteTvShowPaged(): DataSource.Factory<Int, TvShowEntity> {
        return movieDao.getFavoriteTvShowAsPaged()
    }

    fun insertMovie(data: List<MovieEntity>){
        executorService.execute { movieDao.insertMovie(data) }
    }

    fun setMovieState(movieEntity: MovieEntity, state: Boolean){
        movieEntity.favorite = state
        movieDao.updateMovie(movieEntity)
    }

    fun insertTvShow(data: List<TvShowEntity>){
        executorService.execute { movieDao.insertTvShow(data) }
    }

    fun setTvShowState(tvShowEntity: TvShowEntity, state: Boolean){
        tvShowEntity.favorite = state
        movieDao.updateTvShow(tvShowEntity)
    }

}