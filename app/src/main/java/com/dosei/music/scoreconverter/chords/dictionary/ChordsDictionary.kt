package com.dosei.music.scoreconverter.chords.dictionary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.arpeggio.ChordDiagram

@Composable
fun ChordsDictionary(modifier: Modifier = Modifier) {
    val chords = listOf(Chords.A, Chords.B, Chords.C).chunked(2)
    GuitarThumbnailTheme {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(chords) { pairOfChords ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    DrawChord(chord = pairOfChords.getOrNull(0))
                    DrawChord(chord = pairOfChords.getOrNull(1))
                }
            }
        }
    }
}

@Composable
private fun RowScope.DrawChord(chord: Chord?) {
    if (chord != null) {
        ChordDiagram(
            modifier = Modifier.weight(1f),
            name = chord.name,
            components = chord.components
        )
    } else {
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewChordsDictionary() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ChordsDictionary(
            modifier = Modifier
        )
    }
}