package com.dosei.music.scoreconverter.feature.chords.dictionary.data

import com.dosei.music.scoreconverter.toolbox.AssetsReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class LocalChordsRepository(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    private val assetsReader: AssetsReader,
    private val chordParser: ChordParser
) : ChordsRepository {

    override val allChords: Flow<List<Chord>> = flow {
        val chords = withContext(scope.coroutineContext) {
            assetsReader
                .readLines("chords.csv")
                .map { line -> chordParser.parse(line) }
                .toList()
        }
        emit(chords)
    }
}