package com.rbchat.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var myApplication: Application
    }

    override fun onCreate() {
        super.onCreate()
        myApplication = this
    }

}
