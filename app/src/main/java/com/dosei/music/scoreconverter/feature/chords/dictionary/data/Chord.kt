package com.dosei.music.scoreconverter.feature.chords.dictionary.data

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.Component

data class Chord(
    val name: AnnotatedString,
    val components: List<Component>
)
