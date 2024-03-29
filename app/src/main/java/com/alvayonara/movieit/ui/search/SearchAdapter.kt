package com.alvayonara.movieit.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.movieit.BuildConfig
import com.alvayonara.movieit.R
import com.alvayonara.movieit.data.source.local.entity.MovieEntity
import com.alvayonara.movieit.ui.movie.DetailMovieActivity
import com.alvayonara.movieit.ui.movie.DetailMovieActivity.Companion.EXTRA_MOVIE_ID
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_movie.view.*
import org.jetbrains.anko.startActivity

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder = SearchViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_row_movie,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bindItem(listMovies[position])

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(movie: MovieEntity) {
            with(itemView) {
                title_movie_card.text = movie.title
                rating_movie_card.rating = movie.averageVote!!.toFloat() / 2
                vote_average_movie_card.text = movie.averageVote
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