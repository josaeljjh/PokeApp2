package com.jjh.pokeapp2.views.init

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.jjh.pokeapp2.utils.Cons
import com.jjh.pokeapp2.utils.extensions.getBooleanPreference
import com.jjh.pokeapp2.utils.extensions.launchActivity
import com.jjh.pokeapp2.views.home.HomeHost
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
            delay(1000)
            launchActivity<HomeHost>(true)
        }
    }

    private fun modeDark() {
        if(getBooleanPreference(Cons.IS_NIGHT)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun onStart() {
        modeDark()
        super.onStart()
    }
}
