package com.alvayonara.moviecatalogue.ui.settings

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.utils.ToolbarConfig
import kotlinx.android.synthetic.main.activity_settings_about.*

class SettingsAboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_about)

        initToolbar()

        tv_tmdb_license.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_about)
        supportActionBar?.title = "About"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}