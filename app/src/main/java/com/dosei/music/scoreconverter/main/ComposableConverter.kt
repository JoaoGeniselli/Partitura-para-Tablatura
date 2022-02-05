package com.dosei.music.scoreconverter.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.domain.Note
import com.dosei.music.scoreconverter.domain.OctavedNote
import com.dosei.music.scoreconverter.ui.view.ComposableScore
import com.dosei.music.scoreconverter.ui.view.ComposableTablature
import com.dosei.music.scoreconverter.ui.view.NotationNotes

@Composable
fun ComposableConverter(modifier: Modifier = Modifier) {
    val guitar = Guitar.default()
    val noteIndex = remember { mutableStateOf(NotationNotes.E2.index) }
    Column(modifier.padding(16.dp)) {
        ComposableScore(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
                .testTag("score"),
            noteIndex = noteIndex.value,
            onUpdateNoteIndex = { noteIndex.value = it }
        )
        val note = noteIndex.value.toNote()
        ComposableTablature(
            modifier = Modifier.padding(8.dp),
            positions = guitar.tuning.run {
                if (note != null) {
                    mapOf(
                        1 to string1.positionOf(note),
                        2 to string2.positionOf(note),
                        3 to string3.positionOf(note),
                        4 to string4.positionOf(note),
                        5 to string5.positionOf(note),
                        6 to string6.positionOf(note),
                    )
                } else {
                    mapOf()
                }
            }
        )
        Text(text = "Nota Selecionada: ${noteIndex.value.toNote()?.toString()}")
    }
}

private fun Int.toNote(): OctavedNote? {
    return when (NotationNotes.getByIndex(this)) {
        NotationNotes.D6 -> OctavedNote(note = Note.D, octave = 6)
        NotationNotes.C6 -> OctavedNote(note = Note.C, octave = 6)
        NotationNotes.B5 -> OctavedNote(note = Note.B, octave = 5)
        NotationNotes.A5 -> OctavedNote(note = Note.A, octave = 5)
        NotationNotes.G5 -> OctavedNote(note = Note.G, octave = 5)
        NotationNotes.F5 -> OctavedNote(note = Note.F, octave = 5)
        NotationNotes.E5 -> OctavedNote(note = Note.E, octave = 5)
        NotationNotes.D5 -> OctavedNote(note = Note.D, octave = 5)
        NotationNotes.C5 -> OctavedNote(note = Note.C, octave = 5)
        NotationNotes.B4 -> OctavedNote(note = Note.B, octave = 4)
        NotationNotes.A4 -> OctavedNote(note = Note.A, octave = 4)
        NotationNotes.G4 -> OctavedNote(note = Note.G, octave = 4)
        NotationNotes.F4 -> OctavedNote(note = Note.F, octave = 4)
        NotationNotes.E4 -> OctavedNote(note = Note.E, octave = 4)
        NotationNotes.D4 -> OctavedNote(note = Note.D, octave = 4)
        NotationNotes.C4 -> OctavedNote(note = Note.C, octave = 4)
        NotationNotes.B3 -> OctavedNote(note = Note.B, octave = 3)
        NotationNotes.A3 -> OctavedNote(note = Note.A, octave = 3)
        NotationNotes.G3 -> OctavedNote(note = Note.G, octave = 3)
        NotationNotes.F3 -> OctavedNote(note = Note.F, octave = 3)
        NotationNotes.E3 -> OctavedNote(note = Note.E, octave = 3)
        NotationNotes.D3 -> OctavedNote(note = Note.D, octave = 3)
        NotationNotes.C3 -> OctavedNote(note = Note.C, octave = 3)
        NotationNotes.B2 -> OctavedNote(note = Note.B, octave = 2)
        NotationNotes.A2 -> OctavedNote(note = Note.A, octave = 2)
        NotationNotes.G2 -> OctavedNote(note = Note.G, octave = 2)
        NotationNotes.F2 -> OctavedNote(note = Note.F, octave = 2)
        NotationNotes.E2 -> OctavedNote(note = Note.E, octave = 2)
        else -> null
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableConverter() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableConverter(
            modifier = Modifier
        )
    }
}