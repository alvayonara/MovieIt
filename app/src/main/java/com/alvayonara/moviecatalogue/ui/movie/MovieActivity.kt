package com.alvayonara.moviecatalogue.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.utils.ToolbarConfig
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import com.alvayonara.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_movie.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.support.v4.ctx

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

        viewModel.getMovies().observe(this, Observer { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> {
                        progress_bar_movie.visible()
                    }
                    Status.SUCCESS -> {
                        progress_bar_movie.invisible()
                        movieAdapter.submitList(movies.data)
                        movieAdapter.notifyDataSetChanged()
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