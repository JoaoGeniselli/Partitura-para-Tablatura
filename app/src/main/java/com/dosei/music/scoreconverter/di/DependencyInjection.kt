package com.dosei.music.scoreconverter.di

import com.dosei.music.ktransposer.TransposeSong
import com.dosei.music.scoreconverter.converter.MIDINoteConverter
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.feature.chords.dictionary.ChordsDictionaryViewModel
import com.dosei.music.scoreconverter.feature.chords.dictionary.data.ChordParser
import com.dosei.music.scoreconverter.feature.chords.dictionary.data.ChordsRepository
import com.dosei.music.scoreconverter.feature.chords.dictionary.data.LocalChordsRepository
import com.dosei.music.scoreconverter.io.SharedPreferencesClient
import com.dosei.music.scoreconverter.player.PlayerDependencyInjection
import com.dosei.music.scoreconverter.toolbox.BeautifySong
import com.dosei.music.scoreconverter.toolbox.CopyToClipboard
import com.dosei.music.scoreconverter.feature.chords.transposer.TransposerViewModel
import com.dosei.music.scoreconverter.toolbox.AssetsReader
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyInjection {
    val module = module {
        single { Guitar.default() }
        factory { SharedPreferencesClient(androidContext()) }
        factory { PlayerDependencyInjection.createPlayer() }
        single<ChordsRepository> {
            LocalChordsRepository(
                assetsReader = get(),
                chordParser = get()
            )
        }
        factory { ChordParser() }
        factory { AssetsReader(androidContext()) }
        factory { MIDINoteConverter() }
        factory { CopyToClipboard(get()) }
        factory { BeautifySong() }
        factory { TransposeSong() }
        viewModel { TransposerViewModel(get(), get(), get()) }
        viewModel { ChordsDictionaryViewModel(get()) }
    }
}