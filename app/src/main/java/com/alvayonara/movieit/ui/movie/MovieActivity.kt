package com.alvayonara.movieit.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.movieit.R
import com.alvayonara.movieit.utils.ToolbarConfig
import com.alvayonara.movieit.utils.invisible
import com.alvayonara.movieit.utils.visible
import com.alvayonara.movieit.viewmodel.ViewModelFactory
import com.alvayonara.movieit.vo.Status
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        initToolbar()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[MovieViewModel::class.java]

        initView(viewModel)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_movie)
        supportActionBar?.title = "Movie List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    private fun initView(viewModel: MovieViewModel) {
        val movieAdapter = MovieAdapter(MovieAdapter.TYPE_LIST)

        viewModel.movies.observe(this, Observer { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> {
                        progress_bar_movie.visible()
                    }
                    Status.SUCCESS -> {
                        progress_bar_movie.invisible()
                        movieAdapter.setMovies(movies.data)
                    }
                    Status.ERROR -> {
                        progress_bar_movie.invisible()
                        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })

        with(rv_movie) {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}