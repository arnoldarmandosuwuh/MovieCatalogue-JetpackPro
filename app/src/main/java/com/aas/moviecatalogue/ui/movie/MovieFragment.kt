package com.aas.moviecatalogue.ui.movie

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
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.remote.model.MovieModel
import com.aas.moviecatalogue.data.source.vo.Status
import com.aas.moviecatalogue.ui.MainActivity
import com.aas.moviecatalogue.ui.detail.DetailActivity
import com.aas.moviecatalogue.utils.ViewModelFactory
import com.aas.moviecatalogue.utils.invisible
import com.aas.moviecatalogue.utils.visible
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.toast

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var data = mutableListOf<MovieEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(MainActivity.INSTANCE, MovieFragment::class.java.simpleName)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            movieViewModel = obtainViewModel(activity!!)
            movieViewModel.insertBait()
            movieViewModel.movie.observe(viewLifecycleOwner, Observer {
                if (it != null){
                    when (it.status) {
                        Status.LOADING -> {
                            pb_movie.visible()
                        }
                        Status.SUCCESS -> {
                            pb_movie.invisible()
                            it.data?.let { it1 -> movieAdapter.setMovie(it1) }
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            pb_movie.invisible()
                            toast("Error")
                        }
                    }
                }
            })

            movieAdapter = MovieAdapter(requireContext(), data) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(MainActivity.DATA_EXTRA, it)
                intent.putExtra(MainActivity.TYPE, MainActivity.MOVIE)
                startActivity(intent)
            }

            rv_movie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun obtainViewModel(fragmentActivity: FragmentActivity): MovieViewModel {
        val factory = ViewModelFactory.getInstance(fragmentActivity.application)
        return ViewModelProvider(requireActivity(), factory)[MovieViewModel::class.java]
    }
}