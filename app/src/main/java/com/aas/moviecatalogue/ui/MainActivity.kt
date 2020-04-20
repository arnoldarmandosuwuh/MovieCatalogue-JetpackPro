package com.aas.moviecatalogue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.ui.movie.MovieFragment
import com.aas.moviecatalogue.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val DATA_EXTRA = "data"
        const val MOVIE = "movie"
        const val TVSHOW = "tv"
        const val TYPE = "type"
        const val INSTANCE = "instance"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_movie -> {
                    loadMovieFragment()
                }
                R.id.nav_tv -> {
                    loadTvShowFragment()
                }
            }
            true
        }

        if (savedInstanceState == null) {
            nav_view.selectedItemId = R.id.nav_movie
        } else {
            when (savedInstanceState.getString(INSTANCE)) {
                MovieFragment::class.java.simpleName -> {
                    nav_view.selectedItemId = R.id.nav_movie
                }
                TvShowFragment::class.java.simpleName -> {
                    nav_view.selectedItemId = R.id.nav_tv
                }
            }
        }

    }

    private fun loadMovieFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content, MovieFragment(), MovieFragment::class.java.simpleName)
            .commit()
        supportActionBar?.setTitle(R.string.menu_movies)
    }

    private fun loadTvShowFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content, TvShowFragment(), TvShowFragment::class.java.simpleName)
            .commit()
        supportActionBar?.setTitle(R.string.menu_tv_show)
    }
}
