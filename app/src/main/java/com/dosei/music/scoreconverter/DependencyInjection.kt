package com.dosei.music.scoreconverter

import com.dosei.music.scoreconverter.converter.ScoreConverterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyInjection {
    val module by lazy {
        module {
            viewModel { ScoreConverterViewModel() }
        }
    }
}