package com.alvayonara.jetpack_submission_alvayonara.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.alvayonara.jetpack_submission_alvayonara.R
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]

            val movies = viewModel.getMovies()

            initView(movies)
        }
    }

    private fun initView(movies: List<MovieEntity>) {
        val movieAdapter = MovieAdapter()
        movieAdapter.setMovies(movies)

        rv_movie.layoutManager = LinearLayoutManager(ctx)
        rv_movie.setHasFixedSize(true)
        rv_movie.adapter = movieAdapter
    }


}
