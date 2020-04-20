package com.aas.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.RemoteRepository
import com.aas.moviecatalogue.data.source.remote.callback.CallbackGetData
import com.aas.moviecatalogue.data.source.remote.callback.CallbackGetDataDetail
import com.aas.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.aas.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.aas.moviecatalogue.data.source.remote.response.MovieResponse
import com.aas.moviecatalogue.data.source.remote.response.TvShowResponse
import com.aas.moviecatalogue.util.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainRepositoryTest {

    private val remoteRepository = mock(RemoteRepository::class.java)
    private val mainRepository = MainRepository(remoteRepository)
    private val movies = listOf<MovieEntity>()
    private val tvShows = listOf<TvShowEntity>()
    private val detailMovie = DetailMovieResponse(
        "", "", "", "", mutableListOf(), "", "", ""
    )
    private val detailTvShow = DetailTvShowResponse(
        "", mutableListOf(), "", "", "", "", mutableListOf(), ""
    )
    private val idMovie = "495764"
    private val idTvShow = "60735"

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    private fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

    @Test
    fun getMovies() {
        doAnswer {
            (it.arguments[0] as CallbackGetData<MovieEntity, MovieResponse>).onSuccess(movies)
            null
        }.`when`(remoteRepository).getMovie(this.any())

        val result = LiveDataTestUtil.getValues(mainRepository.getMovies())
        verify(remoteRepository, times(1))
            .getMovie(this.any())

        assertNotNull(result)
    }

    @Test
    fun getTvShows() {
        doAnswer {
            (it.arguments[0] as CallbackGetData<TvShowEntity, TvShowResponse>).onSuccess(tvShows)
            null
        }.`when`(remoteRepository).getTvShow(this.any())

        val result = LiveDataTestUtil.getValues(mainRepository.getTvShows())

        verify(remoteRepository, times(1))
            .getTvShow(this.any())

        assertNotNull(result)
    }

    @Test
    fun getDetailMovie() {
        doAnswer {
            (it.arguments[1] as CallbackGetDataDetail<DetailMovieResponse, DetailMovieResponse>).onSuccess(
                detailMovie
            )
            null
        }.`when`(remoteRepository).getDetailMovie(eq(idMovie), this.any())

        val result = LiveDataTestUtil.getValues(mainRepository.getDetailMovie(idMovie))

        verify(remoteRepository, times(1)).getDetailMovie(eq(idMovie), this.any())

        assertNotNull(result)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer {
            (it.arguments[1] as CallbackGetDataDetail<DetailTvShowResponse, DetailTvShowResponse>).onSuccess(
                detailTvShow
            )
            null
        }.`when`(remoteRepository).getDetailTvShow(eq(idTvShow), this.any())

        val result = LiveDataTestUtil.getValues(mainRepository.getDetailTvShow(idTvShow))

        verify(remoteRepository, times(1)).getDetailTvShow(eq(idTvShow), this.any())

        assertNotNull(result)
    }
}

