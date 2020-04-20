package com.aas.moviecatalogue.data.source.remote.response

import com.aas.moviecatalogue.data.source.local.entity.MovieEntity

data class MovieResponse(val results: List<MovieEntity>)