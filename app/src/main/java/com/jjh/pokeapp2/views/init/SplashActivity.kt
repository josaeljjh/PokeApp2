package com.jjh.pokeapp2.views.init

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.views.welcome.home.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.splash)
        //println("url: "+ Crypto.encryptNat("https://pokeapi.co/api/v2/"))
        //println("url: "+ Crypto.decryptNat(BASE_URL_PROD))
        CoroutineScope(Dispatchers.Main).launch {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            delay(1000)
            goToMainActivity()
        }

    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out)
        finish()
    }
}
