package com.alvayonara.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.moviecatalogue.BuildConfig
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.ui.tvshow.DetailTvShowActivity.Companion.EXTRA_TV_SHOW_ID
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_tv_show.view.*
import org.jetbrains.anko.startActivity

class TvShowAdapter internal constructor() :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
                oldItem.tvShowId == newItem.tvShowId

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowViewHolder = TvShowViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_tv_show, parent, false)
    )

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bindItem(course)
        }
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(tvShow: TvShowEntity) {
            with(itemView) {
                title_tv_show_card.text = tvShow.title
                overview_tv_show_card.text = tvShow.overview
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_TMDB_POSTER + tvShow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(poster_tv_show_card)

                setOnClickListener {
                    context.startActivity<DetailTvShowActivity>(EXTRA_TV_SHOW_ID to tvShow.tvShowId)
                }
            }
        }
    }
}