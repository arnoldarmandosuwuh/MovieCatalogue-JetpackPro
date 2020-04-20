package com.aas.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aas.moviecatalogue.data.MovieDataSource
import com.aas.moviecatalogue.data.NetworkBoundResource
import com.aas.moviecatalogue.data.source.local.LocalRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.RemoteRepository
import com.aas.moviecatalogue.data.source.remote.model.MovieModel
import com.aas.moviecatalogue.data.source.remote.model.TvShowModel
import com.aas.moviecatalogue.data.source.remote.response.ApiResponse
import com.aas.moviecatalogue.data.source.vo.Resource
import com.aas.moviecatalogue.utils.AppExecutors

open class MainRepository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    private val pageSize = 5

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieModel>>(appExecutors) {
            override fun loadDatabase(): LiveData<List<MovieEntity>> {
                return localRepository.getMovie()
            }

            override fun shouldFetch(data: List<MovieEntity>): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieModel>>> {
                return remoteRepository.getMovie()
            }

            override fun saveCallResult(data: List<MovieModel>) {
                val movie = mutableListOf<MovieEntity>()
                for (i in data.indices) {
                    movie.add(
                        MovieEntity(
                            data[i].id,
                            data[i].title.toString(),
                            data[i].overview.toString(),
                            data[i].poster_path.toString(),
                            false,
                            data[i].release_date.toString(),
                            data[i].vote_average,
                            data[i].popularity,
                            data[i].backdrop_path.toString()
                        )
                    )
                }
                localRepository.insertMovie(movie)
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowModel>>(appExecutors) {
            override fun saveCallResult(data: List<TvShowModel>) {
                val tvShow = mutableListOf<TvShowEntity>()
                for (i in data.indices) {
                    tvShow.add(
                        TvShowEntity(
                            data[i].id,
                            data[i].name.toString(),
                            data[i].overview.toString(),
                            data[i].poster_path.toString(),
                            false,
                            data[i].first_air_date.toString(),
                            data[i].vote_average,
                            data[i].popularity,
                            data[i].backdrop_path.toString()
                        )
                    )
                }
                localRepository.insertTvShow(tvShow)
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowModel>>> {
                return remoteRepository.getTvShow()
            }

            override fun shouldFetch(data: List<TvShowEntity>): Boolean {
                return data.isNullOrEmpty()
            }

            override fun loadDatabase(): LiveData<List<TvShowEntity>> {
                return localRepository.getTvShow()
            }

        }.asLiveData()
    }

    override fun getMovieFavoritePaged(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieModel>>(appExecutors) {
            override fun saveCallResult(data: List<MovieModel>) {

            }

            override fun createCall(): LiveData<ApiResponse<List<MovieModel>>> {
                return MutableLiveData<ApiResponse<List<MovieModel>>>()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>): Boolean {
                return false
            }

            override fun loadDatabase(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(
                    localRepository.getFavoriteMoviePaged(),
                    pageSize
                ).build()
            }

        }.asLiveData()
    }

    override fun getTvShowFavoritePaged(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowModel>>(appExecutors) {
            override fun saveCallResult(data: List<TvShowModel>) {

            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowModel>>> {
                return MutableLiveData<ApiResponse<List<TvShowModel>>>()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>): Boolean {
                return false
            }

            override fun loadDatabase(): LiveData<PagedList<TvShowEntity>> {
                return LivePagedListBuilder(
                    localRepository.getFavoriteTvShowPaged(),
                    pageSize
                ).build()
            }

        }.asLiveData()
    }

    override fun setMovieState(movieEntity: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localRepository.setMovieState(movieEntity, state)
        }
    }

    override fun setTvShowState(tvShowEntity: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localRepository.setTvShowState(tvShowEntity, state)
        }
    }

}