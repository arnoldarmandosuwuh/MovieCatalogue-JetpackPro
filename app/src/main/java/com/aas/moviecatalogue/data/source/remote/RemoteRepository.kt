package com.aas.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aas.moviecatalogue.BuildConfig
import com.aas.moviecatalogue.data.source.remote.api.ApiRepository
import com.aas.moviecatalogue.data.source.remote.model.MovieModel
import com.aas.moviecatalogue.data.source.remote.model.TvShowModel
import com.aas.moviecatalogue.data.source.remote.response.ApiResponse
import com.aas.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.aas.moviecatalogue.data.source.remote.response.tvshow.TvShowResponse
import com.aas.moviecatalogue.utils.IdleResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteRepository {

    open fun getMovie(): LiveData<ApiResponse<List<MovieModel>>> {
        val liveMovie = MutableLiveData<ApiResponse<List<MovieModel>>>()
        IdleResource.increment()
        ApiRepository.apiServiceInstance().loadMovies(BuildConfig.API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()
                            val movie = mutableListOf<MovieModel>()
                            data?.results?.indices?.forEach { i ->
                                movie.add(
                                    MovieModel(
                                        data.results[i].id,
                                        data.results[i].title,
                                        data.results[i].overview,
                                        data.results[i].poster_path,
                                        data.results[i].release_date,
                                        data.results[i].vote_average,
                                        data.results[i].popularity,
                                        data.results[i].backdrop_path
                                    )
                                )
                            }
                            liveMovie.value = ApiResponse.success(movie)
                            IdleResource.decrement()
                        }
                    }
                }
            })
        return liveMovie
    }

    open fun getTvShow(): LiveData<ApiResponse<List<TvShowModel>>> {
        val liveTvShow = MutableLiveData<ApiResponse<List<TvShowModel>>>()
        IdleResource.increment()
        ApiRepository.apiServiceInstance().loadTvShows(BuildConfig.API_KEY)
            .enqueue(object : Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()
                            val tvShow = mutableListOf<TvShowModel>()
                            data?.results?.indices?.forEach { i ->
                                tvShow.add(
                                    TvShowModel(
                                        data.results[i].id,
                                        data.results[i].name,
                                        data.results[i].overview,
                                        data.results[i].poster_path,
                                        data.results[i].first_air_date,
                                        data.results[i].vote_average,
                                        data.results[i].popularity,
                                        data.results[i].backdrop_path
                                    )
                                )
                            }
                            liveTvShow.value = ApiResponse.success(tvShow)
                            IdleResource.decrement()
                        }
                    }
                }
            })
        return liveTvShow
    }
}