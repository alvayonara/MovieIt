package com.alvayonara.movieit.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alvayonara.movieit.R
import kotlinx.android.synthetic.main.fragment_settings.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lyt_settings_about.setOnClickListener {
            ctx.startActivity<SettingsAboutActivity>()
        }
    }
}