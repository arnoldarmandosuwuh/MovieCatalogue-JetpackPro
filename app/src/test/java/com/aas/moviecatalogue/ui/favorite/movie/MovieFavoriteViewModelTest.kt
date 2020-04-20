package com.aas.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.vo.Resource
import com.aas.moviecatalogue.util.LiveDataTestUtil
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieFavoriteViewModelTest {

    private lateinit var favoriteViewModel: MovieFavoriteViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favoriteViewModel = MovieFavoriteViewModel(mainRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val dataDummy = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<MovieEntity>
        dataDummy.value = Resource.success(pagedList)

        `when`(mainRepository.getMovieFavoritePaged()).thenReturn(dataDummy)

        favoriteViewModel.insertBait()
        favoriteViewModel.favoriteMovie.observeForever(observer as Observer<Resource<PagedList<MovieEntity>>>)

        val result = LiveDataTestUtil.getValues(favoriteViewModel.favoriteMovie)

        verify(observer).onChanged(Resource.success(pagedList))
        verify(mainRepository).getMovieFavoritePaged()
        assertNotNull(result.data)
    }
}