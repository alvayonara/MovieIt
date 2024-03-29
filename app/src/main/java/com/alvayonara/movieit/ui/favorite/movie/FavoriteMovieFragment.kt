package com.alvayonara.movieit.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.movieit.R
import com.alvayonara.movieit.ui.movie.MovieAdapter
import com.alvayonara.movieit.ui.movie.MovieAdapter.Companion.TYPE_LIST
import com.alvayonara.movieit.utils.invisible
import com.alvayonara.movieit.utils.visible
import com.alvayonara.movieit.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.jetbrains.anko.support.v4.ctx

class FavoriteMovieFragment : Fragment() {

    internal lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(
            this,
            factory
        )[FavoriteMovieViewModel::class.java]

        initView(viewModel)
    }

    private fun initView(viewModel: FavoriteMovieViewModel) {
        movieAdapter = MovieAdapter(TYPE_LIST)

        progress_bar_movie_favorite.visible()

        viewModel.getFavoredMovies().observe(viewLifecycleOwner, Observer { movies ->
            progress_bar_movie_favorite.invisible()

            if (movies.isNotEmpty()) {
                movieAdapter.submitList(movies)
                movieAdapter.notifyDataSetChanged()
            } else {
                lyt_empty_movie_favorite.visible()
            }
        })

        with(rv_movie_favorite) {
            layoutManager = LinearLayoutManager(ctx)
            adapter = movieAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        initView(viewModel)
    }
}