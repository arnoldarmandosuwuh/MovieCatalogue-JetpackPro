package com.aas.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.LocalRepository
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.RemoteRepository
import com.aas.moviecatalogue.data.source.vo.Resource
import com.aas.moviecatalogue.util.AppExecutor
import com.aas.moviecatalogue.util.PagedListUtil
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainRepositoryTest {

    private val remoteRepository = mock(RemoteRepository::class.java)
    private val localRepository = mock(LocalRepository::class.java)
    private val appExecutors = mock(AppExecutor::class.java)
    private val mainRepository = MainRepository(remoteRepository, localRepository, appExecutors)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getMovieFavoritePaged() {
        val movie = listOf<MovieEntity>()
        val dataSource = mock(DataSource.Factory::class.java)

        `when`(localRepository.getFavoriteMoviePaged()).thenReturn(dataSource as DataSource.Factory<Int, MovieEntity>)
        mainRepository.getMovieFavoritePaged()

        val result = Resource.success(PagedListUtil.mockPagedList(movie))

        verify(localRepository).getFavoriteMoviePaged()
        assertNotNull(result.data)
    }

    @Test
    fun getTvShowFavoritePaged() {
        val tvShow = listOf<TvShowEntity>()
        val dataSource = mock(DataSource.Factory::class.java)

        `when`(localRepository.getFavoriteTvShowPaged()).thenReturn(dataSource as DataSource.Factory<Int, TvShowEntity>)
        mainRepository.getTvShowFavoritePaged()

        val result = Resource.success(PagedListUtil.mockPagedList(tvShow))

        verify(localRepository).getFavoriteTvShowPaged()
        assertNotNull(result.data)
    }

}

