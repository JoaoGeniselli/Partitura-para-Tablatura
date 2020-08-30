package com.dosei.music.scoreconverter

import com.dosei.music.scoreconverter.converter.ScoreConverterViewModel
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.domain.NotesRepository
import com.dosei.music.scoreconverter.domain.PositionsRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyInjection {
    val module by lazy {
        module {
            single { Guitar.default() }
            factory { NotesRepository() }
            factory { PositionsRepository() }

            viewModel {
                ScoreConverterViewModel(
                    guitar = get(),
                    notesRepository = get(),
                    positionsRepository = get()
                )
            }
        }
    }
}