package com.alvayonara.movieit.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat

object ToolbarConfig {
    fun setWhiteStatusBarColor(act: Activity) {
        // set status bar color to white
        act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        act.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        act.window.statusBarColor = ContextCompat.getColor(act, android.R.color.white)
    }

    fun setLightStatusBar(act: Activity) {
        // set light status bar (android M or up)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val view = act.findViewById<View>(android.R.id.content)
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }
}