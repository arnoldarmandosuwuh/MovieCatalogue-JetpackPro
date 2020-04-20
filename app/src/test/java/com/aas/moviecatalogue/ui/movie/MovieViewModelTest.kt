package com.aas.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieViewModel = MovieViewModel(mainRepository)
    }

    @Test
    fun getMovie() {
        val dataDummy = listOf<MovieEntity>()
        val dataMovies = MutableLiveData<List<MovieEntity>>()
        dataMovies.value = dataDummy

        `when`(mainRepository.getMovies()).thenReturn(dataMovies)
        movieViewModel.getMovie().observeForever(observer as Observer<List<MovieEntity>>)

        verify(observer).onChanged(dataDummy)
        verify(mainRepository).getMovies()
    }
}