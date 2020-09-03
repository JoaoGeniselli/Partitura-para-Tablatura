package com.dosei.music.scoreconverter

import android.app.Application
import com.dosei.music.scoreconverter.di.DependencyInjection
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScoreConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ScoreConverterApplication)
            modules(DependencyInjection.module)
        }
    }
}