package com.aas.moviecatalogue.ui.favorite.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.api.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_list.view.*

class TvShowFavoritePagedAdapter(
    private val context: Context,
    private val onClickListener: (TvShowEntity) -> Unit
) : PagedListAdapter<TvShowEntity, TvShowFavoritePagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

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


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
        fun bindItem(
            data: TvShowEntity,
            onClickListener: (TvShowEntity) -> Unit
        ){
            itemView.tv_title.text = data.name
            Picasso.get().load(ApiRepository.IMAGE_URL + data.posterPath)
                .into(itemView.iv_poster)
            itemView.tv_overview.text = data.overview
            containerView.setOnClickListener { onClickListener(data) }
        }
    }
}