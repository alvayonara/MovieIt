package com.alvayonara.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import com.alvayonara.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.support.v4.ctx

class TvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tv_show, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(
            this,
            factory
        )[TvShowViewModel::class.java]

        initView(viewModel)
    }

    private fun initView(viewModel: TvShowViewModel) {
        val tvShowAdapter = TvShowAdapter()

        viewModel.getTvShows().observe(this, Observer { tvShows ->
            if (tvShows != null) {
                when (tvShows.status) {
                    Status.LOADING -> progress_bar_tv_show.visible()
                    Status.SUCCESS -> {
                        progress_bar_tv_show.invisible()
                        tvShowAdapter.submitList(tvShows.data)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        progress_bar_tv_show.invisible()
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(rv_tv_show) {
            layoutManager = LinearLayoutManager(ctx)
            adapter = tvShowAdapter
        }
    }
}
