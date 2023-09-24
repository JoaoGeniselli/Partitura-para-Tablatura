package com.dosei.music.scoreconverter.feature.chords.dictionary

import com.dosei.music.scoreconverter.feature.chords.dictionary.data.Chord

data class ChordsDictionaryState(
    val content: List<Chord> = emptyList(),
)