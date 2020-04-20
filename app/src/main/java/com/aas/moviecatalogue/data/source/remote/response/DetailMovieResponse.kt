package com.aas.moviecatalogue.data.source.remote.response

data class DetailMovieResponse(
    var id: String?,
    var title: String?,
    var poster_path: String?,
    var release_date: String?,
    var production_companies: List<ProductionCompaniesResponse>,
    var overview: String?,
    var runtime: String?,
    var backdrop_path: String?
)