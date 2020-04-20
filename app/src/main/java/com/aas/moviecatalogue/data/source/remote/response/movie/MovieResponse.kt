package com.aas.moviecatalogue.data.source.remote.response.movie

import com.aas.moviecatalogue.data.source.remote.model.MovieModel

data class MovieResponse(val results: List<MovieModel>)