package com.aas.moviecatalogue.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.remote.api.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_list.view.*

class MovieAdapter(
    private val context: Context,
    private val data: MutableList<MovieEntity>,
    private val onClickListener: (MovieEntity) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    fun setMovie(data: List<MovieEntity>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.items_list, parent, false)
        )


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], onClickListener)
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(
            data: MovieEntity,
            onClickListener: (MovieEntity) -> Unit
        ) {
            itemView.tv_title.text = data.title
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