package com.alvayonara.jetpack_submission_alvayonara.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.jetpack_submission_alvayonara.R
import com.alvayonara.jetpack_submission_alvayonara.utils.invisible
import com.alvayonara.jetpack_submission_alvayonara.utils.visible
import com.alvayonara.jetpack_submission_alvayonara.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.ctx

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]

            initView(viewModel)
        }
    }

    private fun initView(viewModel: MovieViewModel) {
        val movieAdapter = MovieAdapter()

        progress_bar_movie.visible()

        viewModel.getMovies().observe(this, Observer { movies ->
            progress_bar_movie.invisible()

            movieAdapter.setMovies(movies)
            movieAdapter.notifyDataSetChanged()
        })

        with(rv_movie) {
            layoutManager = LinearLayoutManager(ctx)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}
