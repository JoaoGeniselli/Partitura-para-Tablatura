package com.dosei.music.scoreconverter

import com.dosei.music.scoreconverter.domain.Note
import com.dosei.music.scoreconverter.domain.OctavedNote
import org.junit.Assert.*
import org.junit.Test

class OctavedNoteTest {

    @Test
    fun `generate correct name`() {
        val note1 = OctavedNote(
            note = Note.C,
            octave = 2
        )
        val note2 = OctavedNote(
            note = Note.A,
            octave = 4,
            modifier = NoteModifier.SHARP
        )
        val note3 = OctavedNote(
            note = Note.E,
            octave = 5,
            modifier = NoteModifier.FLAT
        )

        assertEquals("C2", note1.name)
        assertEquals("A#4", note2.name)
        assertEquals("Eb5", note3.name)
    }

    @Test
    fun `calculate correct absolute semitone position`() {
        val note1 = OctavedNote(
            note = Note.C,
            octave = 0
        )
        val note2 = OctavedNote(
            note = Note.D,
            octave = 1
        )
        val note3 = OctavedNote(
            note = Note.B,
            octave = 1,
            modifier = NoteModifier.FLAT
        )
        val note4 = OctavedNote(
            note = Note.E,
            octave = 2
        )
        val note5 = OctavedNote(
            note = Note.G,
            octave = 2,
            modifier = NoteModifier.SHARP
        )

        assertEquals(0, note1.absolutePosition)
        assertEquals(14, note2.absolutePosition)
        assertEquals(22, note3.absolutePosition)
        assertEquals(28, note4.absolutePosition)
        assertEquals(32, note5.absolutePosition)
    }

    @Test
    fun `get next natural note`() {
        val note1 = OctavedNote(
            note = Note.C,
            octave = 0
        )
        val note2 = OctavedNote(
            note = Note.B,
            octave = 1
        )
        val note3 = OctavedNote(
            note = Note.A,
            octave = 0,
            modifier = NoteModifier.FLAT
        )
        val note4 = OctavedNote(
            note = Note.F,
            octave = 0,
            modifier = NoteModifier.SHARP
        )

        val next1 = note1.nextNatural
        assertEquals(Note.D, next1.note)
        assertEquals(0, next1.octave)
        assertEquals(null, next1.modifier)

        val next2 = note2.nextNatural
        assertEquals(Note.C, next2.note)
        assertEquals(2, next2.octave)
        assertEquals(null, next2.modifier)

        val next3 = note3.nextNatural
        assertEquals(Note.B, next3.note)
        assertEquals(0, next3.octave)
        assertEquals(null, next3.modifier)

        val next4 = note4.nextNatural
        assertEquals(Note.G, next4.note)
        assertEquals(0, next4.octave)
        assertEquals(null, next4.modifier)
    }

    @Test
    fun `flat test`() {
        val note1 = OctavedNote(
            note = Note.C,
            octave = 0
        )
        val note2 = OctavedNote(
            note = Note.B,
            octave = 1
        )
        val note3 = OctavedNote(
            note = Note.A,
            octave = 0,
            modifier = NoteModifier.FLAT
        )
        val note4 = OctavedNote(
            note = Note.F,
            octave = 0,
            modifier = NoteModifier.SHARP
        )

    }

}