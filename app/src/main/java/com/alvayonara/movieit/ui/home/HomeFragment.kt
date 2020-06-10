package com.alvayonara.movieit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.movieit.R
import com.alvayonara.movieit.ui.movie.MovieActivity
import com.alvayonara.movieit.ui.movie.MovieAdapter
import com.alvayonara.movieit.ui.movie.MovieAdapter.Companion.TYPE_GRID
import com.alvayonara.movieit.ui.search.SearchActivity
import com.alvayonara.movieit.ui.tvshow.TvShowActivity
import com.alvayonara.movieit.ui.tvshow.TvShowAdapter
import com.alvayonara.movieit.utils.Tools
import com.alvayonara.movieit.utils.invisible
import com.alvayonara.movieit.utils.visible
import com.alvayonara.movieit.viewmodel.ViewModelFactory
import com.alvayonara.movieit.vo.Status
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(
            this,
            factory
        )[HomeViewModel::class.java]

        initToolbar()
        initViewMovies(viewModel)
        initViewTvShows(viewModel)

        edt_search_bar.setOnClickListener {
            context?.startActivity<SearchActivity>()
        }

        see_all_movies.setOnClickListener {
            context?.startActivity<MovieActivity>()
        }

        see_all_tv_shows.setOnClickListener {
            context?.startActivity<TvShowActivity>()
        }
    }

    private fun initToolbar() {
        Tools.setSystemBarColor(act, android.R.color.white)
        Tools.setSystemBarLight(act)
    }

    private fun initViewMovies(viewModel: HomeViewModel) {
        val movieAdapter = MovieAdapter(TYPE_GRID)

        viewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> progress_bar_home.visible()
                    Status.SUCCESS -> {
                        progress_bar_home.invisible()
                        movieAdapter.submitList(movies.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        progress_bar_home.invisible()
                        Toast.makeText(
                            context,
                            getString(R.string.error_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

        with(rv_movie_horizontal) {
            layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
        }
    }

    private fun initViewTvShows(viewModel: HomeViewModel) {
        val tvShowAdapter = TvShowAdapter(TYPE_GRID)

        viewModel.getTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            if (tvShows != null) {
                when (tvShows.status) {
                    Status.LOADING -> {
                        progress_bar_home.visible()
                    }
                    Status.SUCCESS -> {
                        progress_bar_home.invisible()
                        tvShowAdapter.submitList(tvShows.data)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        progress_bar_home.invisible()
                        Toast.makeText(
                            context,
                            getString(R.string.error_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

        with(rv_tv_show_horizontal) {
            layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
            adapter = tvShowAdapter
        }
    }
}