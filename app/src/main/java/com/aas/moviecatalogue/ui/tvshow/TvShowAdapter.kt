package com.aas.moviecatalogue.ui.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.remote.api.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_list.view.*

class TvShowAdapter(
    private val context: Context,
    private val data: MutableList<TvShowEntity>,
    private val onClickListener: (TvShowEntity) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    fun setTvShow(data: List<TvShowEntity>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.items_list, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], onClickListener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(
            data: TvShowEntity,
            onClickListener: (TvShowEntity) -> Unit
        ) {
            itemView.tv_title.text = data.name
            if (data.poster_path != null) {
                Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path)
                    .into(itemView.iv_poster)
            } else {
                Picasso.get().load(R.drawable.icons8_image_not_available_100)
                    .into(itemView.iv_poster)
            }
            itemView.tv_overview.text = data.overview
            containerView.setOnClickListener { onClickListener(data) }
        }
    }
}