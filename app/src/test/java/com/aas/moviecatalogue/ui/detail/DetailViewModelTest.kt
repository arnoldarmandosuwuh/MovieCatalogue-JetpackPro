package com.aas.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.aas.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DetailViewModelTest {

    @Mock
    private lateinit var observerMovie: Observer<DetailMovieResponse>

    @Mock
    private lateinit var observerTvShow: Observer<DetailTvShowResponse>

    private lateinit var detailViewModel: DetailViewModel
    private val dataMovie = DetailMovieResponse(
        "dummy",
        "dummy",
        "dummy",
        "dummy",
        mutableListOf(),
        "dummy",
        "dummy",
        "dummy"
    )
    private val dataTvShow = DetailTvShowResponse(
        "dummy",
        mutableListOf(),
        "dummy",
        "dummy",
        "dummy",
        "dummy",
        mutableListOf(),
        "dummy"
    )
    private val mainRepository = mock(MainRepository::class.java)
    private val idMovie = "495764"
    private val idTvShow = "60735"

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailViewModel = DetailViewModel(mainRepository)
        detailViewModel.getDetailMovie(idMovie)
        detailViewModel.getDetailTvShow(idTvShow)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<DetailTvShowResponse>()
        tvShow.value = dataTvShow

        `when`(mainRepository.getDetailTvShow(idTvShow)).thenReturn(tvShow)
        verify(mainRepository).getDetailTvShow(idTvShow)

        detailViewModel.getDetailTvShow(idTvShow)
            .observeForever(observerTvShow)

        verify(observerTvShow).onChanged(dataTvShow)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<DetailMovieResponse>()
        movie.value = dataMovie

        `when`(mainRepository.getDetailMovie(idMovie)).thenReturn(movie)
        verify(mainRepository).getDetailMovie(idMovie)
        detailViewModel.getDetailMovie(idMovie)
            .observeForever(observerMovie)

        verify(observerMovie).onChanged(dataMovie)
    }
}