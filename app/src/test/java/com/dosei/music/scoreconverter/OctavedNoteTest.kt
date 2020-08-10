package com.dosei.music.scoreconverter

import org.junit.Assert.*
import org.junit.Test

class OctavedNoteTest {

    @Test
    fun `generate correct name`() {
        val note1 = OctavedNote(note = Note.C, octave = 2, type = NoteType.NATURAL)
        val note2 = OctavedNote(note = Note.A, octave = 4, type = NoteType.SHARP)
        val note3 = OctavedNote(note = Note.E, octave = 5, type = NoteType.FLAT)

        assertEquals("C2", note1.name)
        assertEquals("A#4", note2.name)
        assertEquals("Eb5", note3.name)
    }

    @Test
    fun `calculate correct absolute semitone position`() {
        val note1 = OctavedNote(note = Note.C, octave = 0, type = NoteType.NATURAL)
        val note2 = OctavedNote(note = Note.D, octave = 1, type = NoteType.NATURAL)
        val note3 = OctavedNote(note = Note.B, octave = 1, type = NoteType.FLAT)
        val note4 = OctavedNote(note = Note.E, octave = 2, type = NoteType.NATURAL)
        val note5 = OctavedNote(note = Note.G, octave = 2, type = NoteType.SHARP)

        assertEquals(0, note1.absoluteSemitonePosition)
        assertEquals(14, note2.absoluteSemitonePosition)
        assertEquals(22, note3.absoluteSemitonePosition)
        assertEquals(28, note4.absoluteSemitonePosition)
        assertEquals(32, note5.absoluteSemitonePosition)
    }

}