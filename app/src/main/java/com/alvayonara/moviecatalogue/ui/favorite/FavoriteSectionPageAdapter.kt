package com.alvayonara.moviecatalogue.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.ui.favorite.movie.FavoriteMovieFragment
import com.alvayonara.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowFragment

class FavoriteSectionPageAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val tabTitles = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvShowFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? =
        context.resources.getString(tabTitles[position])
}