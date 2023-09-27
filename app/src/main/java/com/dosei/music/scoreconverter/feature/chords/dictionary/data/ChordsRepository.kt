package com.dosei.music.scoreconverter.feature.chords.dictionary.data

import com.dosei.music.scoreconverter.feature.chords.dictionary.data.Chord
import kotlinx.coroutines.flow.Flow

interface ChordsRepository {
    val allChords: Flow<List<Chord>>
}