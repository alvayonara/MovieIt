package com.alvayonara.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.ui.tvshow.TvShowAdapter
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.support.v4.ctx

class FavoriteTvShowFragment : Fragment() {

    internal lateinit var viewModel: FavoriteTvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tv_show, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(
            this,
            factory
        )[FavoriteTvShowViewModel::class.java]

        initView(viewModel)
    }

    private fun initView(viewModel: FavoriteTvShowViewModel) {
        val tvShowAdapter = TvShowAdapter()

        progress_bar_tv_show.visible()

        viewModel.getFavoredTvShows().observe(this, Observer { tvShows ->
            progress_bar_tv_show.invisible()
            tvShowAdapter.submitList(tvShows)
            tvShowAdapter.notifyDataSetChanged()
        })

        with(rv_tv_show) {
            layoutManager = LinearLayoutManager(ctx)
            adapter = tvShowAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        initView(viewModel)
    }
}