package com.dosei.music.scoreconverter.chords.dictionary

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.Component

data class Chord(
    val name: AnnotatedString,
    val components: List<Component>
)
