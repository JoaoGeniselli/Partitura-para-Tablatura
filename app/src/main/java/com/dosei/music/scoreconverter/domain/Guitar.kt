package com.dosei.music.scoreconverter.domain

data class Guitar(
    val fretsAmount: Int = 22,
    val tuning: Tuning = Tuning(
        string1 = GuitarString(
            initialNote = OctavedNote(
                note = Note.E,
                octave = 4
            ), frets = fretsAmount
        ),
        string2 = GuitarString(
            initialNote = OctavedNote(
                note = Note.B,
                octave = 3
            ), frets = fretsAmount
        ),
        string3 = GuitarString(
            initialNote = OctavedNote(
                note = Note.G,
                octave = 3
            ), frets = fretsAmount
        ),
        string4 = GuitarString(
            initialNote = OctavedNote(
                note = Note.D,
                octave = 3
            ), frets = fretsAmount
        ),
        string5 = GuitarString(
            initialNote = OctavedNote(
                note = Note.A,
                octave = 2
            ), frets = fretsAmount
        ),
        string6 = GuitarString(
            initialNote = OctavedNote(
                note = Note.E,
                octave = 2
            ), frets = fretsAmount
        )
    )
) {
    companion object {
        private val default by lazy { Guitar() }

        fun default() = default
    }
}