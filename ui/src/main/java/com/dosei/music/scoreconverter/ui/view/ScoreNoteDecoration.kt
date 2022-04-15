package com.dosei.music.scoreconverter.ui.view

import androidx.annotation.DrawableRes
import com.dosei.music.scoreconverter.ui.R

enum class ScoreNoteDecoration(
    @DrawableRes val resource: Int,
    val topPaddingDiff: Float
) {
    NATURAL(R.drawable.ic_natural_note, 0.5f),
    FLAT(R.drawable.ic_flat_black, 0.5f),
    SHARP(R.drawable.ic_sharp_black, 0.3f);
}