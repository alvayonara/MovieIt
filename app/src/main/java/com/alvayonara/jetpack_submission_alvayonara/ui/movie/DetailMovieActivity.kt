package com.alvayonara.jetpack_submission_alvayonara.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.jetpack_submission_alvayonara.DateConvert
import com.alvayonara.jetpack_submission_alvayonara.R
import com.alvayonara.jetpack_submission_alvayonara.ToolbarConfig
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.toolbar

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        initToolbar()

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE_ID)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)

                populateMovie(viewModel.getMovie())
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
        status_movie_detail.text = movie.status
        original_title_movie_detail.text = movie.originalTitle
        original_language_movie_detail.text = movie.originalLanguage
        runtime_movie_detail.text = getString(R.string.runtime, movie.runtime)

        Glide.with(this)
            .load(movie.posterPath)
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
