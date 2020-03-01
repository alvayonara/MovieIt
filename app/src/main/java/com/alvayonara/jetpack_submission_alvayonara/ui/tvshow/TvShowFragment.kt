package com.alvayonara.jetpack_submission_alvayonara.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.alvayonara.jetpack_submission_alvayonara.R
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity
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
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]

            val tvShows = viewModel.getTvShows()

            initView(tvShows)
        }
    }

    private fun initView(tvShows: List<TvShowEntity>) {
        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.setTvShows(tvShows)

        rv_tv_show.layoutManager = LinearLayoutManager(ctx)
        rv_tv_show.setHasFixedSize(true)
        rv_tv_show.adapter = tvShowAdapter
    }
}
