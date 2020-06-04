package com.alvayonara.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.moviecatalogue.BuildConfig
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.ui.movie.DetailMovieActivity.Companion.EXTRA_MOVIE_ID
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_movie.view.*
import org.jetbrains.anko.startActivity

class MovieAdapter internal constructor() :
    PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_row_movie,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bindItem(course)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(movie: MovieEntity) {
            with(itemView) {
                title_movie_card.text = movie.title
                overview_movie_card.text = movie.overview
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_TMDB_POSTER + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(poster_movie_card)

                setOnClickListener {
                    context.startActivity<DetailMovieActivity>(EXTRA_MOVIE_ID to movie.movieId)
                }
            }
        }
    }
}