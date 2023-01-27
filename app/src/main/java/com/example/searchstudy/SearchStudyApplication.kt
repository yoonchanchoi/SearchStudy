package com.example.searchstudy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class SearchStudyApplication : Application() {
    companion object {
        lateinit var instance: SearchStudyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}