package com.jjh.pokeapp2

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.View
import androidx.multidex.MultiDex
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.jjh.pokeapp2.di.module.repositoryModule
import com.jjh.pokeapp2.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App
            private set
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context private set
        var txtBtn = ""

        var view: View? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = instance.applicationContext
        startKoin {
            androidLogger()
            androidContext(this@App)
            // load modules here
            modules(listOf(repositoryModule, viewModelModule))
        }

        //inicializacion sdk facebook
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        view = View(context)

    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}