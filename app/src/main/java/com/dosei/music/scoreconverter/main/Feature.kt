package com.dosei.music.scoreconverter.main

import androidx.annotation.StringRes
import com.dosei.music.scoreconverter.R

enum class Feature(@StringRes val nameRes: Int, val route: String) {
    ScoreToTablature(R.string.menu_item_converter, "converter_tab"),
    ChordsDictionary(R.string.menu_item_chords_dictionary, "dictionary"),
    Transposer(R.string.menu_item_transposer, "transposer")
}