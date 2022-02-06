package com.dosei.music.scoreconverter.main

import com.dosei.music.scoreconverter.domain.Note
import com.dosei.music.scoreconverter.domain.OctavedNote
import com.dosei.music.scoreconverter.ui.view.NotationNotes

fun Int.toNote(): OctavedNote? {
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