package com.alvayonara.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
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
import kotlinx.android.synthetic.main.item_row_movie_horizontal.view.*
import org.jetbrains.anko.startActivity

class MovieAdapter constructor(private val typeView: Int) :
    PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private val dataMovie = arrayListOf<MovieEntity>()

    companion object {
        const val TYPE_LIST = 1
        const val TYPE_GRID = 2

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun submitList(pagedList: PagedList<MovieEntity>?) {
        pagedList?.addWeakCallback(listOf(), object : PagedList.Callback() {
            override fun onChanged(position: Int, count: Int) {
                dataMovie.clear()
                dataMovie.addAll(pagedList)
            }

            override fun onInserted(position: Int, count: Int) {
                dataMovie.clear()
                dataMovie.addAll(pagedList)
            }

            override fun onRemoved(position: Int, count: Int) {
                dataMovie.clear()
                dataMovie.addAll(pagedList)
            }
        })
        super.submitList(pagedList)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return when (typeView) {
            TYPE_LIST -> MovieViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_row_movie,
                    parent,
                    false
                )
            )
            TYPE_GRID -> MovieViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_row_movie_horizontal,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bindItem(course, typeView)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(movie: MovieEntity, typeView: Int) {
            with(itemView) {
                when (typeView) {
                    TYPE_LIST -> {
                        title_movie_card.text = movie.title
                        overview_movie_card.text = movie.overview
                        Glide.with(context)
                            .load(BuildConfig.BASE_URL_TMDB_POSTER + movie.posterPath)
                            .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error)
                            )
                            .into(poster_movie_card)
                    }
                    TYPE_GRID -> {
                        title_movie_card_horizontal.text = movie.title
                        rating_movie_card_horizontal.rating = movie.averageVote!!.toFloat() / 2
                        Glide.with(context)
                            .load(BuildConfig.BASE_URL_TMDB_POSTER + movie.posterPath)
                            .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error)
                            )
                            .into(poster_movie_card_horizontal)
                    }
                    else -> throw IllegalArgumentException("Invalid view type")
                }

                setOnClickListener {
                    context.startActivity<DetailMovieActivity>(EXTRA_MOVIE_ID to movie.movieId)
                }
            }
        }
    }
}