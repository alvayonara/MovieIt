package com.alvayonara.jetpack_submission_alvayonara.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.alvayonara.jetpack_submission_alvayonara.R
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity
import com.alvayonara.jetpack_submission_alvayonara.utils.invisible
import com.alvayonara.jetpack_submission_alvayonara.utils.visible
import com.alvayonara.jetpack_submission_alvayonara.viewmodel.ViewModelFactory
import com.androidnetworking.AndroidNetworking
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.support.v4.ctx

class TvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tv_show, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]

            initView(viewModel)
        }
    }

    private fun initView(viewModel: TvShowViewModel) {
        val tvShowAdapter = TvShowAdapter()

        progress_bar_tv_show.visible()

        viewModel.getTvShows().observe(this, Observer { tvShows ->
            progress_bar_tv_show.invisible()

            tvShowAdapter.setTvShows(tvShows)
            tvShowAdapter.notifyDataSetChanged()
        })

        with(rv_tv_show) {
            layoutManager = LinearLayoutManager(ctx)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }
}
