package com.dosei.music.scoreconverter.di

import Transpose
import com.dosei.music.scoreconverter.converter.MIDINoteConverter
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.io.SharedPreferencesClient
import com.dosei.music.scoreconverter.player.PlayerDependencyInjection
import com.dosei.music.scoreconverter.toolbox.BeautifySong
import com.dosei.music.scoreconverter.toolbox.CopyToClipboard
import com.dosei.music.scoreconverter.transposer.TransposerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyInjection {
    val module by lazy {
        module {
            single { Guitar.default() }
            factory { SharedPreferencesClient(androidContext()) }
            factory { PlayerDependencyInjection.createPlayer() }
            factory { MIDINoteConverter() }
            factory { CopyToClipboard(get()) }
            factory { BeautifySong() }
            factory { Transpose.create(Transpose.fileChordsQuery) }
            viewModel {
                TransposerViewModel(get(), get(), get())
            }
        }
    }
}