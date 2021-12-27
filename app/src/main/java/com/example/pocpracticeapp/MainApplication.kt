package com.example.pocpracticeapp

import android.app.Application
import com.example.imageloader.ImageLoader
import com.example.imageloader.cache.DoubleCache
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}