package com.dutchtechnologies.news_challenge

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        val appComponent = DaggerAppComponent
//            .builder()
//            .application(this)
//            .build()
//        appComponent.inject(this)
//        return appComponent
//    }

    companion object {
        lateinit var instance: App
            private set
    }
}

