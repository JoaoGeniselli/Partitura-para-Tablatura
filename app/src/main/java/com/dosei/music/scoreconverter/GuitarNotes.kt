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

data class GuitarNote(
    val note: String,
    val octave: Int,
    val positions: GuitarPositions
) {
    val name: String get() = note + octave.toString()
}

fun initNaturalNotes(): List<GuitarNote> = listOf(
    GuitarNote(
        note = "E",
        octave = 2,
        positions = GuitarPositions(
            string6 = 0
        )
    ),
    GuitarNote(
        note = "F",
        octave = 2,
        positions = GuitarPositions(
            string6 = 1
        )
    ),
    GuitarNote(
        note = "G",
        octave = 2,
        positions = GuitarPositions(
            string6 = 3
        )
    ),
    GuitarNote(
        note = "A",
        octave = 2,
        positions = GuitarPositions(
            string5 = 0,
            string6 = 5
        )
    ),
    GuitarNote(
        note = "B",
        octave = 2,
        positions = GuitarPositions(
            string5 = 2,
            string6 = 7
        )
    ),
    GuitarNote(
        note = "C",
        octave = 3,
        positions = GuitarPositions(
            string5 = 3,
            string6 = 8
        )
    ),
    GuitarNote(
        note = "D",
        octave = 3,
        positions = GuitarPositions(
            string4 = 0,
            string5 = 5,
            string6 = 10
        )
    ),
    GuitarNote(
        note = "E",
        octave = 3,
        positions = GuitarPositions(
            string4 = 2,
            string5 = 7,
            string6 = 12
        )
    ),
    GuitarNote(
        note = "F",
        octave = 3,
        positions = GuitarPositions(
            string4 = 3,
            string5 = 8,
            string6 = 13
        )
    ),
    GuitarNote(
        note = "G",
        octave = 3,
        positions = GuitarPositions(
            string3 = 0,
            string4 = 5,
            string5 = 10,
            string6 = 15
        )
    ),
    GuitarNote(
        note = "A",
        octave = 3,
        positions = GuitarPositions(
            string3 = 2,
            string4 = 7,
            string5 = 12,
            string6 = 17
        )
    ),
    GuitarNote(
        note = "B",
        octave = 3,
        positions = GuitarPositions(
            string2 = 0,
            string3 = 4,
            string4 = 9,
            string5 = 14,
            string6 = 19
        )
    ),
    GuitarNote(
        note = "C",
        octave = 4,
        positions = GuitarPositions(
            string2 = 1,
            string3 = 5,
            string4 = 10,
            string5 = 15,
            string6 = 20
        )
    ),
    GuitarNote(
        note = "D",
        octave = 4,
        positions = GuitarPositions(
            string2 = 3,
            string3 = 7,
            string4 = 12,
            string5 = 17
        )
    ),
    GuitarNote(
        note = "E",
        octave = 4,
        positions = GuitarPositions(
            string1 = 0,
            string2 = 5,
            string3 = 9,
            string4 = 14,
            string5 = 19
        )
    ),
    GuitarNote(
        note = "F",
        octave = 4,
        positions = GuitarPositions(
            string1 = 1,
            string2 = 6,
            string3 = 10,
            string4 = 15,
            string5 = 20
        )
    ),
    GuitarNote(
        note = "G",
        octave = 4,
        positions = GuitarPositions(
            string1 = 3,
            string2 = 8,
            string3 = 12,
            string4 = 17
        )
    ),
    GuitarNote(
        note = "A",
        octave = 4,
        positions = GuitarPositions(
            string1 = 5,
            string2 = 10,
            string3 = 14,
            string4 = 19
        )
    ),
    GuitarNote(
        note = "B",
        octave = 4,
        positions = GuitarPositions(
            string1 = 7,
            string2 = 12,
            string3 = 16
        )
    ),
    GuitarNote(
        note = "C",
        octave = 5,
        positions = GuitarPositions(
            string1 = 8,
            string2 = 13,
            string3 = 17
        )
    ),
    GuitarNote(
        note = "D",
        octave = 5,
        positions = GuitarPositions(
            string1 = 10,
            string2 = 15,
            string3 = 19
        )
    ),
    GuitarNote(
        note = "E",
        octave = 5,
        positions = GuitarPositions(
            string1 = 12,
            string2 = 17
        )
    ),
    GuitarNote(
        note = "F",
        octave = 5,
        positions = GuitarPositions(
            string1 = 13,
            string2 = 18
        )
    ),
    GuitarNote(
        note = "G",
        octave = 5,
        positions = GuitarPositions(
            string1 = 15,
            string2 = 20
        )
    ),
    GuitarNote(
        note = "A",
        octave = 5,
        positions = GuitarPositions(
            string1 = 17
        )
    ),
    GuitarNote(
        note = "B",
        octave = 5,
        positions = GuitarPositions(
            string1 = 19
        )
    ),
    GuitarNote(
        note = "C",
        octave = 6,
        positions = GuitarPositions(
            string1 = 20
        )
    )
)

data class GuitarPositions(
    val string1: Int? = null,
    val string2: Int? = null,
    val string3: Int? = null,
    val string4: Int? = null,
    val string5: Int? = null,
    val string6: Int? = null
) {
    fun asList() = listOf(string1, string2, string3, string4, string5, string6)
}

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

//fun generateAllNaturalNotes() {
//    val initialNote = Guitar.default().tuning.string6.initialNote
//    val lastNote = Guitar.default().tuning.string1.lastNaturalNote
//
//    var currentNote = initialNote
//    val notes = mutableListOf<OctavedNote>()
//    while (currentNote != lastNote) {
//        notes.add(currentNote)
//        currentNote = currentNote
//    }
//}

fun stringPositionsByNote(initialNote: OctavedNote): Map<OctavedNote, Int> {
    var currentOctave = initialNote.octave
    var currentNote = initialNote.note

    val stringNotes = mutableMapOf<OctavedNote, Int>()
    var fretPosition = 0

    while (fretPosition <= Guitar.default().fretsAmount) {
        if (fretPosition != 0 && currentNote.isOctaveStart()) {
            currentOctave++
        }
        val note = OctavedNote(note = currentNote, octave = currentOctave)
        stringNotes[note] = fretPosition
        fretPosition += currentNote.scalePosition
        currentNote = currentNote.next
    }
    return stringNotes
}

data class Tuning(
    val string1: GuitarString,
    val string2: GuitarString,
    val string3: GuitarString,
    val string4: GuitarString,
    val string5: GuitarString,
    val string6: GuitarString
)

data class StringPositions(
    val string1: Map<OctavedNote, Int>,
    val string2: Map<OctavedNote, Int>,
    val string3: Map<OctavedNote, Int>,
    val string4: Map<OctavedNote, Int>,
    val string5: Map<OctavedNote, Int>,
    val string6: Map<OctavedNote, Int>
) {
    fun allStrings() = listOf(string1, string2, string3, string4, string5, string6)
}

fun allNotesFromStrings(guitarStrings: List<Map<OctavedNote, Int>>): List<OctavedNote> {
    val allNotes = mutableListOf<OctavedNote>()
    guitarStrings.forEach { string ->
        val stringNotes = string.toList()
            .sortedBy { it.second }
            .map { position -> position.first }
        allNotes.addAll(stringNotes)
    }
    return allNotes.distinct()
}

