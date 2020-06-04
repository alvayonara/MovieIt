package com.alvayonara.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import com.alvayonara.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.ctx

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(
            this,
            factory
        )[MovieViewModel::class.java]

        initView(viewModel)
    }

    private fun initView(viewModel: MovieViewModel) {
        val movieAdapter = MovieAdapter()

        viewModel.getMovies().observe(this, Observer { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> progress_bar_movie.visible()
                    Status.SUCCESS -> {
                        progress_bar_movie.invisible()
                        movieAdapter.submitList(movies.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        progress_bar_movie.invisible()
                        Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(rv_movie) {
            layoutManager = LinearLayoutManager(ctx)
            adapter = movieAdapter
        }
    }
}
