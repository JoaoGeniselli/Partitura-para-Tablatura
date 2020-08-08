package com.dosei.music.scoreconverter

import android.app.Application
import org.koin.core.context.startKoin

class ScoreConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(DependencyInjection.module)
        }
    }
}