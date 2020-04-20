package com.aas.moviecatalogue.ui.detail

import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.MovieEntity
import com.aas.moviecatalogue.data.TvShowEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dummyMovie: MovieEntity
    private lateinit var dummyTvShow: TvShowEntity

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
        dummyMovie = MovieEntity(
            "Alita Battle Angel",
            "Alita: Battle Angel is a 2019 American cyberpunk action film based on the 1990s Japanese manga series Gunnm (known as Battle Angel Alita outside Japan) by Yukito Kishiro. Directed by Robert Rodriguez, the film is co-produced by James Cameron and written by Cameron and Laeta Kalogridis. Rosa Salazar stars as the titular heroine Alita, an amnesiac cyborg girl who sets out to learn about her destiny after she awakens in a new body with no past memory of who she is. Christoph Waltz, Jennifer Connelly, Mahershala Ali, Ed Skrein, Jackie Earle Haley and Keean Johnson also star in supporting roles.",
            R.drawable.poster_alita,
            "February 14, 2019",
            "122 minutes",
            "20th Century Fox"
        )
        dummyTvShow = TvShowEntity(
            "Flash",
            "The Flash is an American superhero television series developed by Greg Berlanti, Andrew Kreisberg, and Geoff Johns, airing on The CW. It is based on the DC Comics character Barry Allen / Flash, a costumed superhero crime-fighter with the power to move at superhuman speeds. It is a spin-off from Arrow, existing in the same fictional universe. The series follows Barry Allen, portrayed by Grant Gustin, a crime scene investigator who gains super-human speed, which he uses to fight criminals, including others who have also gained superhuman abilities.",
            R.drawable.poster_flash,
            "October 7, 2014",
            "42–45 minutes",
            "Warner Bros. Television Distribution"
        )
    }

    @Test
    fun getMovieDetail() {
        detailViewModel.setMovieDetail(dummyMovie)
        assertEquals(detailViewModel.title, dummyMovie.title)
        assertEquals(detailViewModel.overview, dummyMovie.overview)
        assertEquals(detailViewModel.poster, dummyMovie.poster)
        assertEquals(detailViewModel.releaseDate, dummyMovie.releaseDate)
        assertEquals(detailViewModel.durationTime, dummyMovie.runningTime)
        assertEquals(detailViewModel.distributedBy, dummyMovie.distributedBy)
    }

    @Test
    fun getTvShowDetail(){
        detailViewModel.setTvShowDetail(dummyTvShow)
        assertEquals(detailViewModel.title, dummyTvShow.title)
        assertEquals(detailViewModel.overview, dummyTvShow.overview)
        assertEquals(detailViewModel.poster, dummyTvShow.poster)
        assertEquals(detailViewModel.releaseDate, dummyTvShow.releaseDate)
        assertEquals(detailViewModel.durationTime, dummyTvShow.runningTime)
        assertEquals(detailViewModel.distributedBy, dummyTvShow.distributedBy)
    }
}