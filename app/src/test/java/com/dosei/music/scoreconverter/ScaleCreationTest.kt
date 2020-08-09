package com.dosei.music.scoreconverter

import org.junit.Assert.assertEquals
import org.junit.Test

class ScaleCreationTest {

    @Test
    fun `create correct string notes`() {
        val lowE = OctavedNote(Note.E, octave = 2)
        val notes = stringPositionsByNote(lowE)

        assertEquals(0, notes[OctavedNote(Note.E, 2)])
        assertEquals(1, notes[OctavedNote(Note.F, 2)])
        assertEquals(3, notes[OctavedNote(Note.G, 2)])
        assertEquals(5, notes[OctavedNote(Note.A, 2)])
        assertEquals(7, notes[OctavedNote(Note.B, 2)])
        assertEquals(8, notes[OctavedNote(Note.C, 3)])
        assertEquals(10, notes[OctavedNote(Note.D, 3)])
        assertEquals(12, notes[OctavedNote(Note.E, 3)])
        assertEquals(13, notes[OctavedNote(Note.F, 3)])
        assertEquals(15, notes[OctavedNote(Note.G, 3)])
        assertEquals(17, notes[OctavedNote(Note.A, 3)])
        assertEquals(19, notes[OctavedNote(Note.B, 3)])
        assertEquals(20, notes[OctavedNote(Note.C, 4)])

        assertEquals(13, notes.size)

        print(notes.toString())

        val lowA = OctavedNote(Note.A, 2)
        val notesAString = stringPositionsByNote(lowA)

        assertEquals(0, notesAString[OctavedNote(Note.A, 2)])
        assertEquals(2, notesAString[OctavedNote(Note.B, 2)])
        assertEquals(3, notesAString[OctavedNote(Note.C, 3)])
        assertEquals(5, notesAString[OctavedNote(Note.D, 3)])
        assertEquals(7, notesAString[OctavedNote(Note.E, 3)])
        assertEquals(8, notesAString[OctavedNote(Note.F, 3)])
        assertEquals(10, notesAString[OctavedNote(Note.G, 3)])
        assertEquals(12, notesAString[OctavedNote(Note.A, 3)])
        assertEquals(14, notesAString[OctavedNote(Note.B, 3)])
        assertEquals(15, notesAString[OctavedNote(Note.C, 4)])
        assertEquals(17, notesAString[OctavedNote(Note.D, 4)])
        assertEquals(19, notesAString[OctavedNote(Note.E, 4)])
        assertEquals(20, notesAString[OctavedNote(Note.F, 4)])

        assertEquals(13, notesAString.size)
    }

    @Test
    fun `all notes`() {
        val lowE = OctavedNote(Note.E, octave = 2)
        val notesEString = stringPositionsByNote(lowE)

        val lowA = OctavedNote(Note.A, 2)
        val notesAString = stringPositionsByNote(lowA)

        val allNotes = allNotesFromStrings(listOf(notesEString, notesAString))

        print(allNotes)

        assertEquals(16, allNotes.size)

        assertEquals(OctavedNote(Note.E, 2), allNotes[0])
        assertEquals(OctavedNote(Note.F, 2), allNotes[1])
        assertEquals(OctavedNote(Note.G, 2), allNotes[2])
        assertEquals(OctavedNote(Note.A, 2), allNotes[3])
        assertEquals(OctavedNote(Note.B, 2), allNotes[4])
        assertEquals(OctavedNote(Note.C, 3), allNotes[5])
        assertEquals(OctavedNote(Note.D, 3), allNotes[6])
        assertEquals(OctavedNote(Note.E, 3), allNotes[7])
        assertEquals(OctavedNote(Note.F, 3), allNotes[8])
        assertEquals(OctavedNote(Note.G, 3), allNotes[9])
        assertEquals(OctavedNote(Note.A, 3), allNotes[10])
        assertEquals(OctavedNote(Note.B, 3), allNotes[11])
        assertEquals(OctavedNote(Note.C, 4), allNotes[12])
        assertEquals(OctavedNote(Note.D, 4), allNotes[13])
        assertEquals(OctavedNote(Note.E, 4), allNotes[14])
        assertEquals(OctavedNote(Note.F, 4), allNotes[15])
    }
}