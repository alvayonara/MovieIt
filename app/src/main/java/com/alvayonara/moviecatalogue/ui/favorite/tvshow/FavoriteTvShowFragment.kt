package com.alvayonara.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.ui.tvshow.TvShowAdapter
import com.alvayonara.moviecatalogue.ui.tvshow.TvShowAdapter.Companion.TYPE_LIST
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.jetbrains.anko.support.v4.ctx

class FavoriteTvShowFragment : Fragment() {

    internal lateinit var viewModel: FavoriteTvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)

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
        tvShowAdapter = TvShowAdapter(TYPE_LIST)

        progress_bar_tv_show_favorite.visible()

        viewModel.getFavoredTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            progress_bar_tv_show_favorite.invisible()

            if (tvShows.isNotEmpty()){
                tvShowAdapter.submitList(tvShows)
                tvShowAdapter.notifyDataSetChanged()
            } else {
                lyt_empty_tv_show_favorite.visible()
            }
        })

        with(rv_tv_show_favorite) {
            layoutManager = LinearLayoutManager(ctx)
            adapter = tvShowAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        initView(viewModel)
    }
}