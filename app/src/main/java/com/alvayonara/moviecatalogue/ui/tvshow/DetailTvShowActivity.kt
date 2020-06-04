package com.alvayonara.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.moviecatalogue.BuildConfig
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.utils.DateConvert
import com.alvayonara.moviecatalogue.utils.ToolbarConfig
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import com.alvayonara.moviecatalogue.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.activity_home.toolbar
import kotlinx.android.synthetic.main.fragment_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {

    internal lateinit var viewModel: DetailTvShowViewModel
    private var menu: Menu? = null

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra_tv_show_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        initToolbar()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString(EXTRA_TV_SHOW_ID)
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)

                viewModel.tvShowDetail.observe(this, Observer { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> progress_bar_detail_tv_show.visible()
                            Status.SUCCESS -> {
                                if (tvShow.data != null) {
                                    progress_bar_detail_tv_show.invisible()
                                    populateTvShow(tvShow.data)
                                }
                            }
                            Status.ERROR -> {
                                progress_bar_tv_show.invisible()
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
        supportActionBar?.title = "Tv Show Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    private fun populateTvShow(tvShow: TvShowEntity) {
        title_tv_show_detail.text = tvShow.title
        vote_average_tv_show_detail.text = tvShow.averageVote
        release_date_tv_show_detail.text = DateConvert.convertDate(tvShow.releaseDate)
        overview_tv_show_detail.text = tvShow.overview
        popularity_tv_show_detail.text = tvShow.popularity
        original_title_tv_show_detail.text = tvShow.originalTitle
        original_language_tv_show_detail.text = tvShow.originalLanguage
        vote_count_tv_show_detail.text = tvShow.voteCount

        Glide.with(this)
            .load(BuildConfig.BASE_URL_TMDB_POSTER + tvShow.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(poster_tv_show_detail)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_favorite -> {
                viewModel.setFavoriteTvShow()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.tvShowDetail.observe(this, Observer { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> progress_bar_detail_tv_show.visible()
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            progress_bar_detail_tv_show.invisible()
                            val state = tvShow.data.favored
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
