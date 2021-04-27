package com.dosei.music.scoreconverter.converter

import com.dosei.music.scoreconverter.domain.Note
import com.dosei.music.scoreconverter.domain.NoteModifier
import com.dosei.music.scoreconverter.domain.OctavedNote
import org.junit.Assert.*
import org.junit.Test

class MIDINoteConverterTest {

    @Test
    fun `test note conversion`() {
        val converter = MIDINoteConverter()
        val a0 = OctavedNote(Note.A, 0)
        val c3 = OctavedNote(Note.C, 3)
        val c3Sharp = OctavedNote(Note.C, 3, NoteModifier.SHARP)

        assertEquals(21, converter.convert(a0))
        assertEquals(48, converter.convert(c3))
        assertEquals(49, converter.convert(c3Sharp))
    }
}
