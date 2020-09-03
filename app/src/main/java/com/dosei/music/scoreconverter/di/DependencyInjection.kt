package com.dosei.music.scoreconverter.di

import com.dosei.music.scoreconverter.converter.ScoreConverterViewModel
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.domain.NotesRepository
import com.dosei.music.scoreconverter.domain.PositionsRepository
import com.dosei.music.scoreconverter.io.SharedPreferencesClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyInjection {
    val module by lazy {
        module {
            single { Guitar.default() }
            factory { NotesRepository() }
            factory { PositionsRepository() }
            factory { SharedPreferencesClient(androidContext()) }

            viewModel {
                ScoreConverterViewModel(
                    guitar = get(),
                    notesRepository = get(),
                    positionsRepository = get(),
                    preferencesClient = get()
                )
            }
        }
    }
}