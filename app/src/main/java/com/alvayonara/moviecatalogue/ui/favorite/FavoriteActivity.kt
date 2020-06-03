package com.alvayonara.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.ui.home.HomeSectionPageAdapter
import com.alvayonara.moviecatalogue.utils.ToolbarConfig
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.toolbar

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        initToolbar()
        initView()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Favorite"

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    private fun initView() {
        val sectionPageAdapter =
            FavoriteSectionPageAdapter(
                this,
                supportFragmentManager
            )
        view_pager_favorite.adapter = sectionPageAdapter
        tabs_favorite.setupWithViewPager(view_pager_favorite)
    }
}