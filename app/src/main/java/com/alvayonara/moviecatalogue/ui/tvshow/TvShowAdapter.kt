package com.alvayonara.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.moviecatalogue.BuildConfig
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.ui.tvshow.DetailTvShowActivity.Companion.EXTRA_TV_SHOW_ID
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_tv_show.view.*
import org.jetbrains.anko.startActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShows = ArrayList<TvShowEntity>()

    fun setTvShows(tvShows: List<TvShowEntity>?) {
        if (tvShows == null) return
        listTvShows.clear()
        listTvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowViewHolder = TvShowViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_tv_show, parent, false)
    )

    override fun getItemCount(): Int = listTvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) =
        holder.bindItem(listTvShows[position])

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(tvShow: TvShowEntity) {
            with(itemView) {
                title_tv_show_card.text = tvShow.title
                overview_tv_show_card.text = tvShow.overview
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_TMDB_POSTER + tvShow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                    )
                    .into(poster_tv_show_card)

                setOnClickListener {
                    context.startActivity<DetailTvShowActivity>(EXTRA_TV_SHOW_ID to tvShow.tvShowId)
                }
            }
        }
    }
}