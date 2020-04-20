package com.aas.moviecatalogue.ui.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.TvShowEntity
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_list.view.*

class TvShowAdapter(
    private val context: Context,
    private val data: MutableList<TvShowEntity>,
    private val onClickListener: (TvShowEntity) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.items_list, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TvShowAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], onClickListener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

        fun bindItem(
            data: TvShowEntity,
            onClickListener: (TvShowEntity) -> Unit
        ){
            itemView.tv_title.text = data.title
            Picasso.get().load(data.poster).into(itemView.iv_poster)
            itemView.tv_overview.text = data.overview
            containerView.setOnClickListener { onClickListener(data) }
        }
    }
}