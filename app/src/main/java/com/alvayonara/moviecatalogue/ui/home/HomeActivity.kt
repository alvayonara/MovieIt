package com.alvayonara.moviecatalogue.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.ui.favorite.FavoriteActivity
import com.alvayonara.moviecatalogue.utils.ToolbarConfig
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initToolbar()
        initView()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Movie Catalogue"

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    private fun initView() {
        val sectionPageAdapter =
            HomeSectionPageAdapter(
                this,
                supportFragmentManager
            )
        view_pager.adapter = sectionPageAdapter
        tabs.setupWithViewPager(view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_to_favorite) {
            val intentToFavorite = Intent(this, FavoriteActivity::class.java)
            startActivity(intentToFavorite)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
