package com.dosei.music.scoreconverter

data class Guitar(
    val fretsAmount: Int = 20,
    val tuning: Tuning = Tuning(
        string1 = GuitarString(initialNote = OctavedNote(note = Note.E, octave = 4), frets = fretsAmount),
        string2 = GuitarString(initialNote = OctavedNote(note = Note.B, octave = 3), frets = fretsAmount),
        string3 = GuitarString(initialNote = OctavedNote(note = Note.G, octave = 3), frets = fretsAmount),
        string4 = GuitarString(initialNote = OctavedNote(note = Note.D, octave = 3), frets = fretsAmount),
        string5 = GuitarString(initialNote = OctavedNote(note = Note.A, octave = 2), frets = fretsAmount),
        string6 = GuitarString(initialNote = OctavedNote(note = Note.E, octave = 2), frets = fretsAmount)
    )
) {
    companion object {
        fun default() = Guitar()
    }
}

data class GuitarPositions(
    val string1: Int? = null,
    val string2: Int? = null,
    val string3: Int? = null,
    val string4: Int? = null,
    val string5: Int? = null,
    val string6: Int? = null
)

const val SCALE_SIZE = 12

enum class Note {
    C {
        override val scalePosition: Int = 0
        override val next: Note by lazy { D }
    },
    D {
        override val scalePosition: Int = 2
        override val next: Note by lazy { E }
    },
    E {
        override val scalePosition: Int = 4
        override val next: Note by lazy { F }
    },
    F {
        override val scalePosition: Int = 5
        override val next: Note by lazy { G }
    },
    G {
        override val scalePosition: Int = 7
        override val next: Note by lazy { A }
    },
    A {
        override val scalePosition: Int = 9
        override val next: Note by lazy { B }
    },
    B {
        override val scalePosition: Int = 11
        override val next: Note by lazy { C }
    };

    abstract val scalePosition: Int
    abstract val next: Note

    fun isOctaveStart() = this == C

    fun isOctaveEnd() = this == B

    companion object {

        fun withIndex(index: Int): Note? {
            return values().firstOrNull { it.scalePosition == index }
        }
    }
}

data class Tuning(
    val string1: GuitarString,
    val string2: GuitarString,
    val string3: GuitarString,
    val string4: GuitarString,
    val string5: GuitarString,
    val string6: GuitarString
)