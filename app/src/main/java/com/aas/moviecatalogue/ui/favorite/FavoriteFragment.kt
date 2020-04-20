package com.aas.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.ui.favorite.movie.MovieFavoriteFragment
import com.aas.moviecatalogue.ui.favorite.tvshow.TvShowFavoriteFragment
import com.aas.moviecatalogue.utils.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPagerAdapter.addFragment(MovieFavoriteFragment())
        viewPagerAdapter.addFragment(TvShowFavoriteFragment())

        vp_favorite.adapter = viewPagerAdapter
        vp_favorite.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val tab = tl_favorite.getTabAt(position)
                tab?.select()
            }

        })

        tl_favorite.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    vp_favorite.currentItem = tab.position
                }
            }

        })
    }

}
