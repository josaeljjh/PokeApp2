package com.jjh.pokeapp2.views.init

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.views.welcome.login.LoginActivity
import kotlinx.coroutines.*


class SplashActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.splash)

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                goToMainActivity()
            }
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out)
        finish()
    }
}
