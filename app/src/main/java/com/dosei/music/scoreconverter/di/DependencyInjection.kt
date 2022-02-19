package com.dosei.music.scoreconverter.di

import com.dosei.music.scoreconverter.converter.MIDINoteConverter
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.io.SharedPreferencesClient
import com.dosei.music.scoreconverter.player.PlayerDependencyInjection
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DependencyInjection {
    val module by lazy {
        module {
            single { Guitar.default() }
            factory { SharedPreferencesClient(androidContext()) }
            factory { PlayerDependencyInjection.createPlayer() }
            factory { MIDINoteConverter() }
        }
    }
}