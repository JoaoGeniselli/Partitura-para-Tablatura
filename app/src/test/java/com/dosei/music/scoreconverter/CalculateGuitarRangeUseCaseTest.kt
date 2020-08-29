package com.dosei.music.scoreconverter

import com.dosei.music.scoreconverter.domain.*
import org.junit.Assert.*
import org.junit.Test

class CalculateGuitarRangeUseCaseTest {

    @Test
    fun `calculate correct natural range`() {
        val calculator = CalculateGuitarRangeUseCase()
        val range = calculator.calculateGeneralRange(Guitar.default())
        assertEquals(
            OctavedNote(
                Note.E,
                2
            ).absolutePosition, range.first)
        assertEquals(
            OctavedNote(
                Note.C,
                6
            ).absolutePosition, range.last)
    }

    @Test
    fun `calculate correct range with sharp end`() {
        val calculator = CalculateGuitarRangeUseCase()
        val range = calculator.calculateGeneralRange(
            Guitar(fretsAmount = 21)
        )
        assertEquals(
            OctavedNote(
                Note.E,
                2
            ).absolutePosition, range.first)
        assertEquals(
            OctavedNote(
                Note.C,
                6,
                NoteModifier.SHARP
            ).absolutePosition, range.last)
    }

    @Test
    fun `calculate correct range with flat end`() {
        val calculator = CalculateGuitarRangeUseCase()
        val range = calculator.calculateGeneralRange(
            Guitar(fretsAmount = 19)
        )
        assertEquals(
            OctavedNote(
                Note.E,
                2
            ).absolutePosition, range.first)
        assertEquals(
            OctavedNote(
                Note.C,
                6,
                NoteModifier.FLAT
            ).absolutePosition, range.last)
    }

    @Test
    fun `calculate correct range with custom tuning`() {
        val calculator = CalculateGuitarRangeUseCase()
        val range = calculator.calculateGeneralRange(
            Guitar(
                fretsAmount = 20,
                tuning = Tuning(
                    string1 = GuitarString(
                        initialNote = OctavedNote(
                            note = Note.E,
                            octave = 4
                        ),
                        frets = 20
                    ),
                    string2 = GuitarString(
                        initialNote = OctavedNote(
                            note = Note.B,
                            octave = 3
                        ),
                        frets = 20
                    ),
                    string3 = GuitarString(
                        initialNote = OctavedNote(
                            note = Note.G,
                            octave = 3
                        ),
                        frets = 20
                    ),
                    string4 = GuitarString(
                        initialNote = OctavedNote(
                            note = Note.D,
                            octave = 3
                        ),
                        frets = 20
                    ),
                    string5 = GuitarString(
                        initialNote = OctavedNote(
                            note = Note.A,
                            octave = 2
                        ),
                        frets = 20
                    ),
                    string6 = GuitarString(
                        initialNote = OctavedNote(
                            note = Note.D,
                            octave = 2
                        ),
                        frets = 20
                    )
                )
            )
        )
        assertEquals(
            OctavedNote(
                Note.D,
                2
            ).absolutePosition, range.first)
        assertEquals(
            OctavedNote(
                Note.C,
                6
            ).absolutePosition, range.last)
    }

    @Test
    fun `generate all notes`() {
        val calculator = CalculateGuitarRangeUseCase()
        val guitar = Guitar.default()
        val allNotes = calculator.generateAllNotes(guitar)

        assertEquals(27, allNotes.size)
        assertEquals(
            OctavedNote(
                Note.E,
                2
            ), allNotes.first())
        assertEquals(
            OctavedNote(
                Note.C,
                6
            ), allNotes.last())
    }

}