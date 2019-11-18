package com.jjh.pokeapp2.views.welcome.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.utils.extensions.configureStatusBarColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dark_mode_radio.check(R.id.follow_system_radio_button)
        dark_mode_radio.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.always_light_radio_button -> AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
                R.id.always_dark_radio_button -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    configureStatusBarColor(R.color.light_black, false)
                }
                R.id.follow_system_radio_button -> AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                )
            }
        }
    }
}
