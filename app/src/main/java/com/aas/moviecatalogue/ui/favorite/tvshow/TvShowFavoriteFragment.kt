package com.aas.moviecatalogue.ui.favorite.tvshow

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
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.vo.Status
import com.aas.moviecatalogue.ui.MainActivity
import com.aas.moviecatalogue.ui.detail.DetailActivity
import com.aas.moviecatalogue.ui.detail.DetailActivity.Companion.RESULT_CODE
import com.aas.moviecatalogue.utils.ViewModelFactory
import com.aas.moviecatalogue.utils.invisible
import com.aas.moviecatalogue.utils.visible
import kotlinx.android.synthetic.main.fragment_movie_favorite.*
import kotlinx.android.synthetic.main.fragment_tv_show_favorite.*
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast

class TvShowFavoriteFragment : Fragment() {

    private lateinit var tvShowFavoritePagedAdapter: TvShowFavoritePagedAdapter
    private lateinit var tvShowFavoriteViewModel: TvShowFavoriteViewModel

    companion object {
        const val REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity!= null){
            tvShowFavoriteViewModel = obtainViewModel(activity!!)

            getFavoriteTvShow()

            tvShowFavoritePagedAdapter = TvShowFavoritePagedAdapter(requireContext()) {
                startActivityForResult<DetailActivity>(
                    REQUEST_CODE, MainActivity.TYPE to MainActivity.TVSHOW,
                    MainActivity.DATA_EXTRA to it
                )
            }

            rv_fav_tv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = tvShowFavoritePagedAdapter
            }
        }
    }

    private fun obtainViewModel(fragmentActivity: FragmentActivity): TvShowFavoriteViewModel {
        val factory = ViewModelFactory.getInstance(fragmentActivity.application)
        return ViewModelProvider(requireActivity(), factory)[TvShowFavoriteViewModel::class.java]
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CODE){
                getFavoriteTvShow()
            }
        }
    }

    private fun getFavoriteTvShow() {
        tvShowFavoriteViewModel.insertBait()
        tvShowFavoriteViewModel.favoriteTvShow.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        pb_fav_tv.invisible()
                        if (it.data!!.isNotEmpty()) {
                            tvShowFavoritePagedAdapter.submitList(it.data)
                            tv_fav_tv_no_data.invisible()
                            rv_fav_tv.visible()
                        } else {
                            tv_fav_tv_no_data.visible()
                            rv_fav_tv.invisible()
                        }
                        tvShowFavoritePagedAdapter.notifyDataSetChanged()
                    }
                    Status.LOADING -> {
                        pb_fav_tv.visible()
                    }
                    Status.ERROR -> {
                        pb_fav_tv.invisible()
                        toast("Error")
                    }
                }
            }
        })
    }

}
