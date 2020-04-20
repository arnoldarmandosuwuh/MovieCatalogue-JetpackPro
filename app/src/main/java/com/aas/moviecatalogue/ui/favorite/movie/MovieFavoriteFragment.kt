package com.aas.moviecatalogue.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.source.vo.Status
import com.aas.moviecatalogue.ui.MainActivity
import com.aas.moviecatalogue.ui.detail.DetailActivity
import com.aas.moviecatalogue.ui.detail.DetailActivity.Companion.RESULT_CODE
import com.aas.moviecatalogue.utils.ViewModelFactory
import com.aas.moviecatalogue.utils.invisible
import com.aas.moviecatalogue.utils.visible
import kotlinx.android.synthetic.main.fragment_movie_favorite.*
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast

class MovieFavoriteFragment : Fragment() {

    private lateinit var movieFavoritePagedAdapter: MovieFavoritePagedAdapter
    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel

    companion object {
        const val REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            movieFavoriteViewModel = obtainViewModel(activity!!)

            getFavoriteMovie()

            movieFavoritePagedAdapter = MovieFavoritePagedAdapter(requireContext()) {
                startActivityForResult<DetailActivity>(
                    REQUEST_CODE,
                    MainActivity.TYPE to MainActivity.MOVIE,
                    MainActivity.DATA_EXTRA to it
                )
            }

            rv_fav_movie.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = movieFavoritePagedAdapter
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CODE) {
                getFavoriteMovie()
            }
        }
    }

    private fun getFavoriteMovie() {
        movieFavoriteViewModel.insertBait()
        movieFavoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        pb_fav_movie.invisible()
                        if (it.data!!.isNotEmpty()) {
                            movieFavoritePagedAdapter.submitList(it.data)
                            tv_fav_movie_no_data.invisible()
                            rv_fav_movie.visible()
                        } else {
                            tv_fav_movie_no_data.visible()
                            rv_fav_movie.invisible()
                        }
                        movieFavoritePagedAdapter.notifyDataSetChanged()
                    }
                    Status.LOADING -> {
                        pb_fav_movie.visible()
                        tv_fav_movie_no_data.invisible()
                    }
                    Status.ERROR -> {
                        pb_fav_movie.invisible()
                        toast("Error")
                    }
                }
            }
        })
    }

    private fun obtainViewModel(fragmentActivity: FragmentActivity): MovieFavoriteViewModel {
        val factory = ViewModelFactory.getInstance(fragmentActivity.application)
        return ViewModelProvider(requireActivity(), factory)[MovieFavoriteViewModel::class.java]
    }

}
