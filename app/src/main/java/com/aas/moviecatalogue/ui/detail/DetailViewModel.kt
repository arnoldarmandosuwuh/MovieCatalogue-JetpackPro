package com.aas.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.aas.moviecatalogue.data.MovieEntity
import com.aas.moviecatalogue.data.TvShowEntity

class DetailViewModel : ViewModel() {
    var title = ""
    var poster = 0
    var releaseDate = ""
    var durationTime = ""
    var distributedBy = ""
    var overview = ""

    fun setMovieDetail(movie: MovieEntity) {
        title = movie.title
        poster = movie.poster
        releaseDate = movie.releaseDate
        durationTime = movie.runningTime
        distributedBy = movie.distributedBy
        overview = movie.overview
    }

    fun setTvShowDetail(tvShow: TvShowEntity) {
        title = tvShow.title
        poster = tvShow.poster
        releaseDate = tvShow.releaseDate
        durationTime = tvShow.runningTime
        distributedBy = tvShow.distributedBy
        overview = tvShow.overview
    }
}