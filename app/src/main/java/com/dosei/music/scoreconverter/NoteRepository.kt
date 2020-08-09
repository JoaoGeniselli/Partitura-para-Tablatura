package com.dosei.music.scoreconverter

class NoteRepository {

    private val tuning = Tuning(
        string1 = OctavedNote(note = Note.E, octave = 4),
        string2 = OctavedNote(note = Note.B, octave = 3),
        string3 = OctavedNote(note = Note.G, octave = 3),
        string4 = OctavedNote(note = Note.D, octave = 3),
        string5 = OctavedNote(note = Note.A, octave = 2),
        string6 = OctavedNote(note = Note.E, octave = 2)
    )

    private val stringPositions: StringPositions
    private val allNotes: List<OctavedNote>

    init {
        stringPositions = StringPositions(
            string1 = stringPositionsByNote(tuning.string1),
            string2 = stringPositionsByNote(tuning.string2),
            string3 = stringPositionsByNote(tuning.string3),
            string4 = stringPositionsByNote(tuning.string4),
            string5 = stringPositionsByNote(tuning.string5),
            string6 = stringPositionsByNote(tuning.string6)
        )
        allNotes = allNotesFromStrings(stringPositions.allStrings().reversed())
    }

    fun findAllNotes() = allNotes

    fun notePositions(note: OctavedNote): GuitarPositions {
        return GuitarPositions(
            string1 = stringPositions.string1[note],
            string2 = stringPositions.string2[note],
            string3 = stringPositions.string3[note],
            string4 = stringPositions.string4[note],
            string5 = stringPositions.string5[note],
            string6 = stringPositions.string6[note]
        )
    }
}