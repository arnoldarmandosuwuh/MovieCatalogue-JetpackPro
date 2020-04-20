package com.aas.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.MovieEntity
import com.aas.moviecatalogue.data.TvShowEntity
import com.aas.moviecatalogue.ui.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]
        val type = intent.getStringExtra(MainActivity.TYPE)

        if (type == MainActivity.MOVIE) {
            val data = intent.getParcelableExtra<MovieEntity>(MainActivity.DATA_EXTRA)
            detailViewModel.setMovieDetail(data)
            initData()
        } else if (type == MainActivity.TVSHOW) {
            val data = intent.getParcelableExtra<TvShowEntity>(MainActivity.DATA_EXTRA)
            detailViewModel.setTvShowDetail(data)
            initData()
        }

    }

    private fun initData() {
        supportActionBar?.title = detailViewModel.title
        Picasso.get().load(detailViewModel.poster).into(iv_poster_detail)
        tv_title_detail.text = detailViewModel.title
        tv_release_date_detail.text = detailViewModel.releaseDate
        tv_duration_time_detail.text = detailViewModel.durationTime
        tv_distributed_by_detail.text = detailViewModel.distributedBy
        tv_overview_detail.text = detailViewModel.overview
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
}
