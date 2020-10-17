package com.alvayonara.movieit.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.movieit.BuildConfig
import com.alvayonara.movieit.R
import com.alvayonara.movieit.domain.model.TvShow
import com.alvayonara.movieit.ui.movie.MovieAdapter
import com.alvayonara.movieit.ui.tvshow.DetailTvShowActivity.Companion.EXTRA_TV_SHOW_ID
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_tv_show.view.*
import kotlinx.android.synthetic.main.item_row_tv_show_horizontal.view.*
import org.jetbrains.anko.startActivity

class TvShowAdapter constructor(private val typeView: Int) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShows = ArrayList<TvShow>()

    fun setTvShows(tvShows: List<TvShow>?) {
        if (tvShows == null) return
        listTvShows.clear()
        listTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    companion object {
        const val TYPE_LIST = 1
        const val TYPE_GRID = 2
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowViewHolder {
        return when (typeView) {
            MovieAdapter.TYPE_LIST -> TvShowViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_row_tv_show,
                    parent,
                    false
                )
            )
            MovieAdapter.TYPE_GRID -> TvShowViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_row_tv_show_horizontal,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = listTvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bindItem(listTvShows[position], typeView)
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(tvShow: TvShow, typeView: Int) {
            with(itemView) {
                when (typeView) {
                    TYPE_LIST -> {
                        tvShow.let {
                            title_tv_show_card.text = it.title
                            rating_tv_show_card.rating = it.averageVote.toFloat() / 2
                            vote_average_tv_show_card.text = it.averageVote
                            overview_tv_show_card.text = it.overview
                            Glide.with(context)
                                .load(BuildConfig.BASE_URL_TMDB_POSTER + it.posterPath)
                                .apply(
                                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error)
                                )
                                .into(poster_tv_show_card)
                        }
                    }
                    TYPE_GRID -> {
                        tvShow.let {
                            title_tv_show_card_horizontal.text = it.title
                            rating_tv_show_card_horizontal.rating = (it.averageVote.toFloat() / 2)
                            Glide.with(context)
                                .load(BuildConfig.BASE_URL_TMDB_POSTER + it.posterPath)
                                .apply(
                                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error)
                                )
                                .into(poster_tv_show_card_horizontal)
                        }
                    }
                }

                setOnClickListener {
                    context.startActivity<DetailTvShowActivity>(EXTRA_TV_SHOW_ID to tvShow.tvShowId)
                }
            }
        }
    }
}