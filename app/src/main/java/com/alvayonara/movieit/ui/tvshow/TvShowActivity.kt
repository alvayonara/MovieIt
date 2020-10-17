package com.alvayonara.movieit.ui.tvshow

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
import kotlinx.android.synthetic.main.activity_tv_show.*

class TvShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_tv_show
        )

        initToolbar()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[TvShowViewModel::class.java]

        initView(viewModel)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_tv_show)
        supportActionBar?.title = "TV Show List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    private fun initView(viewModel: TvShowViewModel) {
        val tvShowAdapter = TvShowAdapter(TvShowAdapter.TYPE_LIST)

        viewModel.tvShows.observe(this, Observer { tvShows ->
            if (tvShows != null) {
                when (tvShows.status) {
                    Status.LOADING -> progress_bar_tv_show.visible()
                    Status.SUCCESS -> {
                        progress_bar_tv_show.invisible()
                        tvShowAdapter.setTvShows(tvShows.data)
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

        with(rv_tv_show) {
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowAdapter
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