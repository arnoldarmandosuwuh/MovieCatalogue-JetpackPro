package com.aas.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.remote.model.MovieModel
import com.aas.moviecatalogue.data.source.vo.Resource
import com.aas.moviecatalogue.util.LiveDataTestUtil
import org.junit.Assert.assertNotNull
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
        val dataDummy = Resource.success(listOf<MovieEntity>())
        val dataMovies = MutableLiveData<Resource<List<MovieEntity>>>()
        dataMovies.value = dataDummy

        `when`(mainRepository.getMovies()).thenReturn(dataMovies)

        movieViewModel.insertBait()
        movieViewModel.movie.observeForever(observer as Observer<Resource<List<MovieEntity>>>)

        val result = LiveDataTestUtil.getValues(movieViewModel.movie)

        verify(observer).onChanged(dataDummy)
        verify(mainRepository).getMovies()
        assertNotNull(result.data)
    }
}