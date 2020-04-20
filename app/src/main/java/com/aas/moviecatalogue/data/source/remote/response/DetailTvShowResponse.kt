package com.aas.moviecatalogue.data.source.remote.response

data class DetailTvShowResponse(
    var id: String?,
    var episode_run_time: List<Int>,
    var first_air_date: String?,
    var name: String?,
    var overview: String?,
    var poster_path: String?,
    var production_companies: List<ProductionCompaniesResponse>,
    var backdrop_path: String?
)