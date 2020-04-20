package com.aas.moviecatalogue.ui.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShow = tvShowViewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals(10, tvShow.size)
    }
}