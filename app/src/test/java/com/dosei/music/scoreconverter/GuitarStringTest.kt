package com.dosei.music.scoreconverter

import com.dosei.music.scoreconverter.domain.GuitarString
import com.dosei.music.scoreconverter.domain.Note
import com.dosei.music.scoreconverter.domain.OctavedNote
import org.junit.Assert.*
import org.junit.Test

class GuitarStringTest {

    @Test
    fun `generate correct note range`() {
        val lowE = OctavedNote(
            note = Note.E,
            octave = 2
        )
        val string =
            GuitarString(initialNote = lowE)

        // This is a test, remove me later
        assertEquals(28, string.noteRange.first)
        assertEquals(48, string.noteRange.last)

        print(
            OctavedNote(
                note = Note.C,
                octave = 6
            ).absolutePosition)
    }
}