package com.alvayonara.moviecatalogue.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.moviecatalogue.BuildConfig
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.utils.DateConvert
import com.alvayonara.moviecatalogue.utils.ToolbarConfig
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import com.alvayonara.moviecatalogue.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    internal lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        initToolbar()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE_ID)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)

                viewModel.movieDetail.observe(this, Observer { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> progress_bar_detail_movie.visible()
                            Status.SUCCESS -> {
                                if (movie.data != null) {
                                    progress_bar_detail_movie.invisible()
                                    populateMovie(movie.data)
                                }
                            }
                            Status.ERROR -> {
                                progress_bar_detail_movie.invisible()
                                Toast.makeText(
                                    this,
                                    getString(R.string.error_message),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Movie Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    private fun populateMovie(movie: MovieEntity) {
        title_movie_detail.text = movie.title
        rating_movie.rating = movie.averageVote!!.toFloat() / 2
        vote_average_movie_detail.text = movie.averageVote
        release_date_movie_detail.text = DateConvert.convertDate(movie.releaseDate)
        overview_movie_detail.text = movie.overview
        popularity_movie_detail.text = movie.popularity
        original_title_movie_detail.text = movie.originalTitle
        original_language_movie_detail.text = movie.originalLanguage
        vote_count_movie_detail.text = movie.voteCount

        Glide.with(this)
            .load(BuildConfig.BASE_URL_TMDB_POSTER + movie.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(
                poster_movie_detail
            )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_favorite -> {
                viewModel.setFavoriteMovie()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.movieDetail.observe(this, Observer { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> progress_bar_detail_movie.visible()
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            progress_bar_detail_movie.invisible()
                            val state = movie.data.favored
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            this,
                            getString(R.string.error_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
        return true
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favored)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }
}
