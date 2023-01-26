package com.example.searchstudy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class SearchApplication : Application() {
    companion object {
        lateinit var instance: SearchApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}