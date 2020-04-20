package com.aas.moviecatalogue.ui.tvshow

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
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.model.TvShowModel
import com.aas.moviecatalogue.data.source.vo.Status
import com.aas.moviecatalogue.ui.MainActivity
import com.aas.moviecatalogue.ui.detail.DetailActivity
import com.aas.moviecatalogue.utils.ViewModelFactory
import com.aas.moviecatalogue.utils.invisible
import com.aas.moviecatalogue.utils.visible
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.support.v4.toast

class TvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var tvShowViewModel: TvShowViewModel
    private var data = mutableListOf<TvShowEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(MainActivity.INSTANCE, TvShowFragment::class.java.simpleName)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            tvShowViewModel = obtainViewModel(activity!!)
            tvShowViewModel.insertBait()
            tvShowViewModel.tvShow.observe(viewLifecycleOwner, Observer {
              if (it != null){
                  when(it.status){
                      Status.ERROR -> {
                          pb_tv.invisible()
                          toast("Error")
                      }
                      Status.LOADING -> {
                          pb_tv.visible()
                      }
                      Status.SUCCESS -> {
                          pb_tv.invisible()
                          it.data?.let { it1 -> tvShowAdapter.setTvShow(it1) }
                          tvShowAdapter.notifyDataSetChanged()
                      }
                  }
              }
            })

            tvShowAdapter = TvShowAdapter(requireContext(), data) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(MainActivity.DATA_EXTRA, it)
                intent.putExtra(MainActivity.TYPE, MainActivity.TVSHOW)
                startActivity(intent)
            }

            rv_tv.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    private fun obtainViewModel(fragmentActivity: FragmentActivity): TvShowViewModel {
        val factory = ViewModelFactory.getInstance(fragmentActivity.application)
        return ViewModelProvider(requireActivity(), factory)[TvShowViewModel::class.java]
    }
}
