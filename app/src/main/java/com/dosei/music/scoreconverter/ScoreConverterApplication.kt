package com.dosei.music.scoreconverter

import android.app.Application
import com.dosei.music.scoreconverter.di.DependencyInjection
import com.google.android.gms.ads.MobileAds
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScoreConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ScoreConverterApplication)
            modules(DependencyInjection.module)
        }
        MobileAds.initialize(this) { }
    }
}