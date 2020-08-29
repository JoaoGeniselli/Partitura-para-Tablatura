package com.dosei.music.scoreconverter

import org.junit.Assert.*
import org.junit.Test

class IntervalCalculatorTest {

    @Test
    fun `calculate correct intervals`() {
        val calculator = IntervalCalculator()
        val sameNote = calculator.calculateSemitonesBetween(
            initialNote = OctavedNote(Note.E, octave = 2),
            finalNote = OctavedNote(Note.E, octave = 2)
        )
        val oneOctave = calculator.calculateSemitonesBetween(
            initialNote = OctavedNote(Note.E, octave = 2),
            finalNote = OctavedNote(Note.E, octave = 3)
        )
        val eToGSharp = calculator.calculateSemitonesBetween(
            initialNote = OctavedNote(Note.E, octave = 2),
            finalNote = OctavedNote(Note.G, octave = 2, modifier = NoteModifier.SHARP)
        )
        val eToBFlat = calculator.calculateSemitonesBetween(
            initialNote = OctavedNote(Note.E, octave = 2),
            finalNote = OctavedNote(Note.B, octave = 2, modifier = NoteModifier.FLAT)
        )
        assertEquals(0, sameNote)
        assertEquals(12, oneOctave)
        assertEquals(4, eToGSharp)
        assertEquals(6, eToBFlat)
    }
}