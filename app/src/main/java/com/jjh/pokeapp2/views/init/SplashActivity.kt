package com.jjh.pokeapp2.views.init

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.views.welcome.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.splash)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            goToMainActivity()
        }

    }

    private fun goToMainActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out)
        finish()
    }
}
