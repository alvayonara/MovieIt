package com.alvayonara.movieit.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.movieit.BuildConfig
import com.alvayonara.movieit.R
import com.alvayonara.movieit.data.source.local.entity.MovieEntity
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.ui.movie.DetailMovieActivity
import com.alvayonara.movieit.ui.movie.DetailMovieActivity.Companion.EXTRA_MOVIE_ID
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_movie.view.*
import org.jetbrains.anko.startActivity

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var listMovies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
        notifyDataSetChanged()
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
        fun bindItem(movie: Movie) {
            with(itemView) {
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

                setOnClickListener {
                    context.startActivity<DetailMovieActivity>(EXTRA_MOVIE_ID to movie.movieId)
                }
            }
        }
    }
}