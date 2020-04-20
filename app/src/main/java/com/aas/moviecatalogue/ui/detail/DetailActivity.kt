package com.aas.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.api.ApiRepository
import com.aas.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.aas.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.aas.moviecatalogue.ui.MainActivity
import com.aas.moviecatalogue.utils.ViewModelFactory
import com.aas.moviecatalogue.utils.invisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(MainActivity.DATA_EXTRA) && intent.hasExtra(MainActivity.TYPE)) {
            val id = intent.getStringExtra(MainActivity.DATA_EXTRA)

            detailViewModel = obtainViewModel()
            when (intent.getStringExtra(MainActivity.TYPE)) {
                MainActivity.MOVIE -> {
                    detailViewModel.getDetailMovie(id).observe(this, observerDetailMovie)
                }
                MainActivity.TVSHOW -> {
                    detailViewModel.getDetailTvShow(id).observe(this, observerDetailTvShow)
                }
            }
        }

    }

    private fun initDetailMovie(data: DetailMovieResponse) {
        supportActionBar?.title = data.title

        if (data.poster_path != null) {
            Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(iv_poster_detail)
        } else {
            Picasso.get().load(R.drawable.icons8_image_not_available_100).into(iv_poster_detail)
        }

        if (data.backdrop_path != null) {
            Picasso.get().load(ApiRepository.IMAGE_URL + data.backdrop_path).into(iv_backdrop)
        } else {
            Picasso.get().load(R.drawable.no_image).into(iv_backdrop)
        }
        tv_title_detail.text = data.title

        val date = LocalDate.parse(data.release_date)
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
        val formattedDate = date.format(formatter)

        tv_release_date_detail.text = formattedDate

        tv_duration_time_detail.text = String.format("${data.runtime} %1s", "minutes")
        tv_overview_detail.text = data.overview

        var companies = ""
        for (i in data.production_companies.indices) {
            var comma = ", "
            if (i == data.production_companies.size - 1) {
                comma = ""
            }
            companies += (data.production_companies[i].name + comma)
        }
        tv_distributed_by_detail.text = companies

    }

    private fun initDetailTvShow(data: DetailTvShowResponse) {
        supportActionBar?.title = data.name

        if (data.poster_path != null) {
            Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(iv_poster_detail)
        } else {
            Picasso.get().load(R.drawable.icons8_image_not_available_100).into(iv_poster_detail)
        }

        if (data.backdrop_path != null) {
            Picasso.get().load(ApiRepository.IMAGE_URL + data.backdrop_path).into(iv_backdrop)
        } else {
            Picasso.get().load(R.drawable.no_image).into(iv_backdrop)
        }
        tv_title_detail.text = data.name

        val date = LocalDate.parse(data.first_air_date)
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
        val formattedDate = date.format(formatter)

        tv_release_date_detail.text = formattedDate
        tv_overview_detail.text = data.overview

        val runtime = if (data.episode_run_time.size > 1) {
            "${data.episode_run_time[0]} - ${data.episode_run_time[1]} minutes"
        } else {
            "${data.episode_run_time[0]} minutes"
        }

        tv_duration_time_detail.text = runtime

        var companies = ""
        for (i in data.production_companies.indices) {
            var comma = ", "
            if (i == data.production_companies.size - 1) {
                comma = ""
            }
            companies += (data.production_companies[i].name + comma)
        }

        tv_distributed_by_detail.text = companies
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun obtainViewModel(): DetailViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private val observerDetailMovie = Observer<DetailMovieResponse> {
        if (it != null) {
            initDetailMovie(it)
            pb_detail.invisible()
        }
    }

    private val observerDetailTvShow = Observer<DetailTvShowResponse> {
        if (it != null) {
            initDetailTvShow(it)
            pb_detail.invisible()
        }
    }
}
