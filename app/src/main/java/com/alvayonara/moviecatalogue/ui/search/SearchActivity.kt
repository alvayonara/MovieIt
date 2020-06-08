package com.alvayonara.moviecatalogue.ui.search

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.ui.movie.MovieAdapter
import com.alvayonara.moviecatalogue.ui.movie.MovieAdapter.Companion.TYPE_LIST
import com.alvayonara.moviecatalogue.ui.movie.MovieViewModel
import com.alvayonara.moviecatalogue.utils.ToolbarConfig
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import com.alvayonara.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.jetbrains.anko.support.v4.ctx

class SearchActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initToolbar()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[SearchViewModel::class.java]

        initView(viewModel)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    private fun initView(viewModel: SearchViewModel) {
        movieAdapter = MovieAdapter(TYPE_LIST)

        lyt_search.invisible()
        lyt_not_found_search.invisible()

        viewModel.getMovieSearch("%when%").observe(this, Observer { movies ->
            when (movies.status) {
                Status.LOADING -> {
                    lyt_search.invisible()
                    progress_bar_movie_search.visible()
                }
                Status.SUCCESS -> {
                    progress_bar_movie_search.invisible()

                    if (movies.data!!.isEmpty()) {
                        lyt_not_found_search.visible()
                    } else {
                        movieAdapter.submitList(movies.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    progress_bar_movie_search.invisible()
                    Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        with(rv_movie_search) {
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