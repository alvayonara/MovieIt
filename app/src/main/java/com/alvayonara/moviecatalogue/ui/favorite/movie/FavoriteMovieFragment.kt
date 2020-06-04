package com.alvayonara.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.ui.movie.MovieAdapter
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.ctx

class FavoriteMovieFragment : Fragment() {

    internal lateinit var viewModel: FavoriteMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

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
        val movieAdapter = MovieAdapter()

        progress_bar_movie.visible()

        viewModel.getFavoredMovies().observe(this, Observer { movies ->
            progress_bar_movie.invisible()
            movieAdapter.submitList(movies)
            movieAdapter.notifyDataSetChanged()
        })

        with(rv_movie) {
            layoutManager = LinearLayoutManager(ctx)
            adapter = movieAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        initView(viewModel)
    }
}