package com.aas.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.TvShowEntity
import com.aas.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShow() : MutableList<TvShowEntity> = DataDummy.generateTvShowData()
}