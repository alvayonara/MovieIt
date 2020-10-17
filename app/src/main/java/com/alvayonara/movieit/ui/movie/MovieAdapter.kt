package com.alvayonara.movieit.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.movieit.BuildConfig
import com.alvayonara.movieit.R
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.ui.movie.DetailMovieActivity.Companion.EXTRA_MOVIE_ID
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_movie.view.*
import kotlinx.android.synthetic.main.item_row_movie_horizontal.view.*
import org.jetbrains.anko.startActivity

class MovieAdapter constructor(private val typeView: Int) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<Movie>()

    companion object {
        const val TYPE_LIST = 1
        const val TYPE_GRID = 2
    }

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
        notifyDataSetChanged()
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

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bindItem(listMovies[position], typeView)

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(movie: Movie, typeView: Int) {
            with(itemView) {
                when (typeView) {
                    TYPE_LIST -> {
                        movie.let {
                            title_movie_card.text = it.title
                            rating_movie_card.rating = it.averageVote.toFloat() / 2
                            vote_average_movie_card.text = it.averageVote
                            overview_movie_card.text = it.overview
                            Glide.with(context)
                                .load(BuildConfig.BASE_URL_TMDB_POSTER + it.posterPath)
                                .apply(
                                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error)
                                )
                                .into(poster_movie_card)
                        }
                    }
                    TYPE_GRID -> {
                        movie.let {
                            title_movie_card_horizontal.text = it.title
                            rating_movie_card_horizontal.rating = it.averageVote.toFloat() / 2
                            Glide.with(context)
                                .load(BuildConfig.BASE_URL_TMDB_POSTER + it.posterPath)
                                .apply(
                                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error)
                                )
                                .into(poster_movie_card_horizontal)
                        }
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