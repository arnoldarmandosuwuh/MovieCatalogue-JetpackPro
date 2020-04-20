package com.aas.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.vo.Resource
import com.aas.moviecatalogue.util.LiveDataTestUtil
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tvShowViewModel = TvShowViewModel(mainRepository)
    }

    @Test
    fun getTvShow() {
        val dataDummy = Resource.success(listOf<TvShowEntity>())
        val dataTvShows = MutableLiveData<Resource<List<TvShowEntity>>>()
        dataTvShows.value = dataDummy

        `when`(mainRepository.getTvShows()).thenReturn(dataTvShows)

        tvShowViewModel.insertBait()
        tvShowViewModel.tvShow.observeForever(observer as Observer<Resource<List<TvShowEntity>>>)

        val result = LiveDataTestUtil.getValues(tvShowViewModel.tvShow)

        verify(observer).onChanged(dataDummy)
        verify(mainRepository).getTvShows()
        assertNotNull(result.data)
    }
}