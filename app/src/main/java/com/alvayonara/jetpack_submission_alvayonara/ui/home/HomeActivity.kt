package com.alvayonara.jetpack_submission_alvayonara.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alvayonara.jetpack_submission_alvayonara.R
import com.alvayonara.jetpack_submission_alvayonara.ToolbarConfig
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
}
