package com.aas.moviecatalogue.ui.detail

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.api.ApiRepository
import com.aas.moviecatalogue.ui.MainActivity
import com.aas.moviecatalogue.utils.ViewModelFactory
import com.aas.moviecatalogue.utils.invisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var movieEntity: MovieEntity
    private lateinit var tvShowEntity: TvShowEntity
    private lateinit var menuItem: Menu
    private lateinit var type: String

    companion object {
        const val RESULT_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(MainActivity.DATA_EXTRA) && intent.hasExtra(MainActivity.TYPE)) {
            detailViewModel = obtainViewModel(application)
            when (intent.getStringExtra(MainActivity.TYPE)) {
                MainActivity.MOVIE -> {
                    type = MainActivity.MOVIE
                    movieEntity = intent.getParcelableExtra(MainActivity.DATA_EXTRA)
                    detailViewModel.detailMovie.value = movieEntity
                    detailViewModel.detailMovie.observe(this, observerDetailMovie)
                }
                MainActivity.TVSHOW -> {
                    type = MainActivity.TVSHOW
                    tvShowEntity = intent.getParcelableExtra(MainActivity.DATA_EXTRA)
                    detailViewModel.detailTv.value = tvShowEntity
                    detailViewModel.detailTv.observe(this, observerDetailTvShow)
                }
            }
        }
    }

    private fun initDetailMovie(data: MovieEntity) {
        supportActionBar?.title = data.title

        Picasso.get().load(ApiRepository.IMAGE_URL + data.posterPath).into(iv_poster_detail)
        Picasso.get().load(ApiRepository.IMAGE_URL + data.backdropPath).into(iv_backdrop)

        tv_title_detail.text = data.title

        val date = LocalDate.parse(data.releaseDate)
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
        val formattedDate = date.format(formatter)
        tv_release_date_detail.text = formattedDate

        tv_overview_detail.text = data.overview
        tv_popularity.text = data.popularity.toString()
        tv_score.text = data.voteAverage.toString()
    }

    private fun initDetailTvShow(data: TvShowEntity) {
        supportActionBar?.title = data.name

        Picasso.get().load(ApiRepository.IMAGE_URL + data.posterPath).into(iv_poster_detail)
        Picasso.get().load(ApiRepository.IMAGE_URL + data.backdropPath).into(iv_backdrop)

        tv_title_detail.text = data.name

        val date = LocalDate.parse(data.firstAirDate)
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
        val formattedDate = date.format(formatter)

        tv_release_date_detail.text = formattedDate
        tv_overview_detail.text = data.overview
        tv_popularity.text = data.popularity.toString()
        tv_score.text = data.voteAverage.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        favoriteState()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.fav -> {
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        when (type) {
            MainActivity.MOVIE -> {
                if (movieEntity.favorite) {
                    menuItem.getItem(0).icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                } else {
                    menuItem.getItem(0).icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                }
            }
            MainActivity.TVSHOW -> {
                if (tvShowEntity.favorite) {
                    menuItem.getItem(0).icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                } else {
                    menuItem.getItem(0).icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                }
            }
        }
    }

    private fun setFavorite() {
        when (type) {
            MainActivity.MOVIE -> {
                if (movieEntity.favorite) {
                    detailViewModel.setMovieState(movieEntity, false)
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                    toast(resources.getString(R.string.remove_favorite))
                } else {
                    detailViewModel.setMovieState(movieEntity, true)
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                    toast(resources.getString(R.string.add_favorite))
                }
            }
            MainActivity.TVSHOW -> {
                if (tvShowEntity.favorite) {
                    detailViewModel.setTvShowState(tvShowEntity, false)
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                    toast(resources.getString(R.string.remove_favorite))
                } else {
                    detailViewModel.setTvShowState(tvShowEntity, true)
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                    toast(resources.getString(R.string.add_favorite))
                }
            }
        }
    }

    private fun obtainViewModel(application: Application): DetailViewModel {
        val factory = ViewModelFactory.getInstance(application)
        return ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private val observerDetailMovie = Observer<MovieEntity> {
        if (it != null) {
            initDetailMovie(it)
            pb_detail.invisible()
        }
    }

    private val observerDetailTvShow = Observer<TvShowEntity> {
        if (it != null) {
            initDetailTvShow(it)
            pb_detail.invisible()
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_CODE)
        finish()
    }
}
