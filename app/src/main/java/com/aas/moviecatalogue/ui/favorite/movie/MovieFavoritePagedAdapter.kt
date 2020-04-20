package com.aas.moviecatalogue.ui.favorite.movie

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.remote.api.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_list.view.*

class MovieFavoritePagedAdapter(
    private val context: Context,
    private val onClickListener: (MovieEntity) -> Unit
) : PagedListAdapter<MovieEntity, MovieFavoritePagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(
            data: MovieEntity,
            onClickListener: (MovieEntity) -> Unit
        ){
            itemView.tv_title.text = data.title
            Picasso.get().load(ApiRepository.IMAGE_URL + data.posterPath)
                .into(itemView.iv_poster)
            itemView.tv_overview.text = data.overview
            containerView.setOnClickListener { onClickListener(data) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.items_list, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItem(position) != null){
            val movie = getItem(position)
            movie?.let { holder.bindItem(it, onClickListener) }
        }
    }
}