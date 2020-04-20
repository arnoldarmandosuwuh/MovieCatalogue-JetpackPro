package com.aas.moviecatalogue.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.vo.Resource
import com.aas.moviecatalogue.util.LiveDataTestUtil
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TvShowFavoriteViewModelTest {

    private lateinit var favoriteViewModel: TvShowFavoriteViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favoriteViewModel = TvShowFavoriteViewModel(mainRepository)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataDummy = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<TvShowEntity>
        dataDummy.value = Resource.success(pagedList)

        `when`(mainRepository.getTvShowFavoritePaged()).thenReturn(dataDummy)

        favoriteViewModel.insertBait()
        favoriteViewModel.favoriteTvShow.observeForever(observer as Observer<Resource<PagedList<TvShowEntity>>>)

        val result = LiveDataTestUtil.getValues(favoriteViewModel.favoriteTvShow)

        verify(observer).onChanged(Resource.success(pagedList))
        verify(mainRepository).getTvShowFavoritePaged()
        assertNotNull(result.data)
    }
}