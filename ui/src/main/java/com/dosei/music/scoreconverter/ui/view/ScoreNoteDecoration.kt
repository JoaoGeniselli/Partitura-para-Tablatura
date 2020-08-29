package com.dosei.music.scoreconverter.ui.view

import androidx.annotation.DrawableRes
import com.dosei.music.scoreconverter.ui.R

enum class ScoreNoteDecoration(
    @DrawableRes val resource: Int
) {
    FLAT(R.drawable.ic_flat_black),
    SHARP(R.drawable.ic_sharp_black)
}