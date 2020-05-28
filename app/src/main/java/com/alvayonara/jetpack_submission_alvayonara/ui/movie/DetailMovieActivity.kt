package com.alvayonara.jetpack_submission_alvayonara.ui.movie

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.jetpack_submission_alvayonara.BuildConfig
import com.alvayonara.jetpack_submission_alvayonara.utils.DateConvert
import com.alvayonara.jetpack_submission_alvayonara.R
import com.alvayonara.jetpack_submission_alvayonara.utils.ToolbarConfig
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.alvayonara.jetpack_submission_alvayonara.utils.invisible
import com.alvayonara.jetpack_submission_alvayonara.utils.visible
import com.alvayonara.jetpack_submission_alvayonara.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_home.toolbar

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        initToolbar()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE_ID)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)

                progress_bar_detail_movie.visible()

                viewModel.getMovie().observe(this, Observer { movie ->
                    progress_bar_detail_movie.invisible()

                    populateMovie(movie)
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}
