package com.dosei.music.scoreconverter

import com.dosei.music.scoreconverter.converter.ScoreConverterViewModel
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.domain.NotesRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyInjection {
    val module by lazy {
        module {
            single { Guitar.default() }
            single { NotesRepository() }
            viewModel {
                ScoreConverterViewModel(
                    guitar = get(),
                    calculator = get()
                )
            }
        }
    }
}