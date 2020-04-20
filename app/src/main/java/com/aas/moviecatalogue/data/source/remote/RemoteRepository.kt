package com.aas.moviecatalogue.data.source.remote

import com.aas.moviecatalogue.BuildConfig
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.api.ApiRepository
import com.aas.moviecatalogue.data.source.remote.callback.*
import com.aas.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.aas.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.aas.moviecatalogue.data.source.remote.response.MovieResponse
import com.aas.moviecatalogue.data.source.remote.response.TvShowResponse
import com.aas.moviecatalogue.utils.IdleResource
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteRepository {

    open fun getMovie(callbackGetMovie: CallbackGetData<MovieEntity, MovieResponse>) {
        IdleResource.increment()
        ApiRepository.apiServiceInstance().loadMovies(BuildConfig.API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    callbackGetMovie.onFailed(call, t)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()
                            val movie = mutableListOf<MovieEntity>()
                            data?.results?.indices?.forEach { i ->
                                movie.add(
                                    MovieEntity(
                                        data.results[i].id,
                                        data.results[i].title,
                                        data.results[i].overview,
                                        data.results[i].poster_path
                                    )
                                )
                            }
                            callbackGetMovie.onSuccess(movie)
                            IdleResource.decrement()
                        }
                    }
                }

            })
    }

    open fun getTvShow(callbackGetTvShow: CallbackGetData<TvShowEntity, TvShowResponse>) {
        IdleResource.increment()
        ApiRepository.apiServiceInstance().loadTvShows(BuildConfig.API_KEY)
            .enqueue(object : Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    callbackGetTvShow.onFailed(call, t)
                }

                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()
                            val tvShow = mutableListOf<TvShowEntity>()
                            data?.results?.indices?.forEach { i ->
                                tvShow.add(
                                    TvShowEntity(
                                        data.results[i].id,
                                        data.results[i].name,
                                        data.results[i].overview,
                                        data.results[i].poster_path
                                    )
                                )
                            }
                            callbackGetTvShow.onSuccess(tvShow)
                            IdleResource.decrement()
                        }
                    }
                }
            })
    }

    open fun getDetailMovie(
        id: String,
        callbackGetDetailMovie: CallbackGetDataDetail<DetailMovieResponse, DetailMovieResponse>
    ) {
        IdleResource.increment()
        ApiRepository.apiServiceInstance().loadMovieDetail(id, BuildConfig.API_KEY)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    callbackGetDetailMovie.onFailed(call, t)
                }

                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()
                            data?.let { callbackGetDetailMovie.onSuccess(it) }
                            IdleResource.decrement()
                        }
                    }
                }

            })
    }

    open fun getDetailTvShow(
        id: String,
        callbackGetDetailTvShow: CallbackGetDataDetail<DetailTvShowResponse, DetailTvShowResponse>
    ) {
        IdleResource.increment()
        ApiRepository.apiServiceInstance().loadTVShowDetail(id, BuildConfig.API_KEY)
            .enqueue(object : Callback<DetailTvShowResponse> {
                override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                    callbackGetDetailTvShow.onFailed(call, t)
                }

                override fun onResponse(
                    call: Call<DetailTvShowResponse>,
                    response: Response<DetailTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()
                            data?.let { callbackGetDetailTvShow.onSuccess(it) }
                            IdleResource.decrement()
                        }
                    }
                }

            })
    }
}