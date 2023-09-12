package com.dosei.music.scoreconverter.main

import androidx.annotation.StringRes
import com.dosei.music.scoreconverter.R

enum class Feature(@StringRes val nameRes: Int) {
    ScoreToTablature(R.string.menu_item_converter),
    ChordsDictionary(R.string.menu_item_chords_dictionary),
    Transposer(R.string.menu_item_transposer)
}