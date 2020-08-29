package com.dosei.music.scoreconverter

import org.junit.Assert.*
import org.junit.Test

class GuitarStringTest {

    @Test
    fun `generate correct note range`() {
        val lowE = OctavedNote(note = Note.E, octave = 2)
        val string = GuitarString(initialNote = lowE)

        assertEquals(28, string.noteRange.first)
        assertEquals(48, string.noteRange.last)

        print(OctavedNote(note = Note.C, octave = 6).absolutePosition)
    }
}