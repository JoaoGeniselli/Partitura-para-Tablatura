package com.dosei.music.scoreconverter.main

import com.dosei.music.arpeggio.ScoreNoteDecoration
import com.dosei.music.scoreconverter.domain.Note
import com.dosei.music.scoreconverter.domain.NoteModifier
import com.dosei.music.scoreconverter.domain.OctavedNote
import com.dosei.music.scoreconverter.ui.view.NotationNotes

fun Int.toNote(decoration: ScoreNoteDecoration): OctavedNote? {
    val modifier = when(decoration) {
        ScoreNoteDecoration.FLAT -> NoteModifier.FLAT
        ScoreNoteDecoration.SHARP -> NoteModifier.SHARP
        else -> null
    }
    return when (NotationNotes.getByIndex(this)) {
        NotationNotes.D6 -> OctavedNote(note = Note.D, octave = 6, modifier = modifier)
        NotationNotes.C6 -> OctavedNote(note = Note.C, octave = 6, modifier = modifier)
        NotationNotes.B5 -> OctavedNote(note = Note.B, octave = 5, modifier = modifier)
        NotationNotes.A5 -> OctavedNote(note = Note.A, octave = 5, modifier = modifier)
        NotationNotes.G5 -> OctavedNote(note = Note.G, octave = 5, modifier = modifier)
        NotationNotes.F5 -> OctavedNote(note = Note.F, octave = 5, modifier = modifier)
        NotationNotes.E5 -> OctavedNote(note = Note.E, octave = 5, modifier = modifier)
        NotationNotes.D5 -> OctavedNote(note = Note.D, octave = 5, modifier = modifier)
        NotationNotes.C5 -> OctavedNote(note = Note.C, octave = 5, modifier = modifier)
        NotationNotes.B4 -> OctavedNote(note = Note.B, octave = 4, modifier = modifier)
        NotationNotes.A4 -> OctavedNote(note = Note.A, octave = 4, modifier = modifier)
        NotationNotes.G4 -> OctavedNote(note = Note.G, octave = 4, modifier = modifier)
        NotationNotes.F4 -> OctavedNote(note = Note.F, octave = 4, modifier = modifier)
        NotationNotes.E4 -> OctavedNote(note = Note.E, octave = 4, modifier = modifier)
        NotationNotes.D4 -> OctavedNote(note = Note.D, octave = 4, modifier = modifier)
        NotationNotes.C4 -> OctavedNote(note = Note.C, octave = 4, modifier = modifier)
        NotationNotes.B3 -> OctavedNote(note = Note.B, octave = 3, modifier = modifier)
        NotationNotes.A3 -> OctavedNote(note = Note.A, octave = 3, modifier = modifier)
        NotationNotes.G3 -> OctavedNote(note = Note.G, octave = 3, modifier = modifier)
        NotationNotes.F3 -> OctavedNote(note = Note.F, octave = 3, modifier = modifier)
        NotationNotes.E3 -> OctavedNote(note = Note.E, octave = 3, modifier = modifier)
        NotationNotes.D3 -> OctavedNote(note = Note.D, octave = 3, modifier = modifier)
        NotationNotes.C3 -> OctavedNote(note = Note.C, octave = 3, modifier = modifier)
        NotationNotes.B2 -> OctavedNote(note = Note.B, octave = 2, modifier = modifier)
        NotationNotes.A2 -> OctavedNote(note = Note.A, octave = 2, modifier = modifier)
        NotationNotes.G2 -> OctavedNote(note = Note.G, octave = 2, modifier = modifier)
        NotationNotes.F2 -> OctavedNote(note = Note.F, octave = 2, modifier = modifier)
        NotationNotes.E2 -> OctavedNote(note = Note.E, octave = 2, modifier = modifier)
        else -> null
    }
}