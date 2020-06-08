package com.alvayonara.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alvayonara.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.support.v4.act

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        // init ViewPager TabLayout
        val sectionPageAdapter =
            FavoriteSectionPageAdapter(
                act,
                childFragmentManager
            )
        view_pager_favorite.adapter = sectionPageAdapter
        tabs_favorite.setupWithViewPager(view_pager_favorite)
    }
}