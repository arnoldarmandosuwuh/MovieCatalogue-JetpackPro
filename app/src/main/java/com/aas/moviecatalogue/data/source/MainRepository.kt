package com.aas.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aas.moviecatalogue.data.MovieDataSource
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.RemoteRepository
import com.aas.moviecatalogue.data.source.remote.callback.*
import com.aas.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.aas.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.aas.moviecatalogue.data.source.remote.response.MovieResponse
import com.aas.moviecatalogue.data.source.remote.response.TvShowResponse
import com.aas.moviecatalogue.utils.TAG
import retrofit2.Call

open class MainRepository(private val remoteRepository: RemoteRepository) : MovieDataSource {

    val liveDataMovie = MutableLiveData<List<MovieEntity>>()
    val liveDataTvShow = MutableLiveData<List<TvShowEntity>>()
    val liveDataDetailMovie = MutableLiveData<DetailMovieResponse>()
    val liveDataDetailTvShow = MutableLiveData<DetailTvShowResponse>()

    override fun getMovies(): LiveData<List<MovieEntity>> {
        remoteRepository.getMovie(object : CallbackGetData<MovieEntity, MovieResponse> {
            override fun onSuccess(data: List<MovieEntity>) {
                liveDataMovie.postValue(data)
            }

            override fun onFailed(call: Call<MovieResponse>, e: Throwable) {
                Log.e(TAG, e.printStackTrace().toString())
            }

        })
        return liveDataMovie
    }

    override fun getTvShows(): LiveData<List<TvShowEntity>> {
        remoteRepository.getTvShow(object : CallbackGetData<TvShowEntity, TvShowResponse> {
            override fun onSuccess(data: List<TvShowEntity>) {
                liveDataTvShow.postValue(data)
            }

            override fun onFailed(call: Call<TvShowResponse>, e: Throwable) {
                Log.e(TAG, e.printStackTrace().toString())
            }

        })
        return liveDataTvShow
    }

    override fun getDetailMovie(id: String): LiveData<DetailMovieResponse> {
        remoteRepository.getDetailMovie(
            id,
            object : CallbackGetDataDetail<DetailMovieResponse, DetailMovieResponse> {
                override fun onSuccess(data: DetailMovieResponse) {
                    liveDataDetailMovie.postValue(data)
                }

                override fun onFailed(call: Call<DetailMovieResponse>, e: Throwable) {
                    Log.e(TAG, e.printStackTrace().toString())
                }
            })
        return liveDataDetailMovie
    }

    override fun getDetailTvShow(id: String): LiveData<DetailTvShowResponse> {
        remoteRepository.getDetailTvShow(
            id,
            object : CallbackGetDataDetail<DetailTvShowResponse, DetailTvShowResponse> {
                override fun onSuccess(data: DetailTvShowResponse) {
                    liveDataDetailTvShow.postValue(data)
                }

                override fun onFailed(call: Call<DetailTvShowResponse>, e: Throwable) {
                    Log.e(TAG, e.printStackTrace().toString())
                }

            })
        return liveDataDetailTvShow
    }

}