package com.jjh.pokeapp2.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.jjh.pokeapp2.di.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App private set
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context private set
        var txtBtn = ""

    }



    override fun onCreate() {
        super.onCreate()
        instance = this
        context = instance.applicationContext
        startKoin {
            androidLogger()
            androidContext(this@App)
            // load modules here
            modules(networkModule.networkModule)
        }

        //inicializacion sdk facebook
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}