package com.dosei.music.scoreconverter

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
            chord6 = 0
        )
    ),
    GuitarNote(
        note = "F",
        octave = 2,
        positions = GuitarPositions(
            chord6 = 1
        )
    ),
    GuitarNote(
        note = "G",
        octave = 2,
        positions = GuitarPositions(
            chord6 = 3
        )
    ),
    GuitarNote(
        note = "A",
        octave = 2,
        positions = GuitarPositions(
            chord5 = 0,
            chord6 = 5
        )
    ),
    GuitarNote(
        note = "B",
        octave = 2,
        positions = GuitarPositions(
            chord5 = 2,
            chord6 = 7
        )
    ),
    GuitarNote(
        note = "C",
        octave = 3,
        positions = GuitarPositions(
            chord5 = 3,
            chord6 = 8
        )
    ),
    GuitarNote(
        note = "D",
        octave = 3,
        positions = GuitarPositions(
            chord4 = 0,
            chord5 = 5,
            chord6 = 10
        )
    ),
    GuitarNote(
        note = "E",
        octave = 3,
        positions = GuitarPositions(
            chord4 = 2,
            chord5 = 7,
            chord6 = 12
        )
    ),
    GuitarNote(
        note = "F",
        octave = 3,
        positions = GuitarPositions(
            chord4 = 3,
            chord5 = 8,
            chord6 = 13
        )
    ),
    GuitarNote(
        note = "G",
        octave = 3,
        positions = GuitarPositions(
            chord3 = 0,
            chord4 = 5,
            chord5 = 10,
            chord6 = 15
        )
    ),
    GuitarNote(
        note = "A",
        octave = 3,
        positions = GuitarPositions(
            chord3 = 2,
            chord4 = 7,
            chord5 = 12,
            chord6 = 17
        )
    ),
    GuitarNote(
        note = "B",
        octave = 3,
        positions = GuitarPositions(
            chord2 = 0,
            chord3 = 4,
            chord4 = 9,
            chord5 = 14,
            chord6 = 19
        )
    ),
    GuitarNote(
        note = "C",
        octave = 4,
        positions = GuitarPositions(
            chord2 = 1,
            chord3 = 5,
            chord4 = 10,
            chord5 = 15,
            chord6 = 20
        )
    ),
    GuitarNote(
        note = "D",
        octave = 4,
        positions = GuitarPositions(
            chord2 = 3,
            chord3 = 7,
            chord4 = 12,
            chord5 = 17
        )
    ),
    GuitarNote(
        note = "E",
        octave = 4,
        positions = GuitarPositions(
            chord1 = 0,
            chord2 = 5,
            chord3 = 9,
            chord4 = 14,
            chord5 = 19
        )
    ),
    GuitarNote(
        note = "F",
        octave = 4,
        positions = GuitarPositions(
            chord1 = 1,
            chord2 = 6,
            chord3 = 10,
            chord4 = 15,
            chord5 = 20
        )
    ),
    GuitarNote(
        note = "G",
        octave = 4,
        positions = GuitarPositions(
            chord1 = 3,
            chord2 = 8,
            chord3 = 12,
            chord4 = 17
        )
    ),
    GuitarNote(
        note = "A",
        octave = 4,
        positions = GuitarPositions(
            chord1 = 5,
            chord2 = 10,
            chord3 = 14,
            chord4 = 19
        )
    ),
    GuitarNote(
        note = "B",
        octave = 4,
        positions = GuitarPositions(
            chord1 = 7,
            chord2 = 12,
            chord3 = 16
        )
    ),
    GuitarNote(
        note = "C",
        octave = 5,
        positions = GuitarPositions(
            chord1 = 8,
            chord2 = 13,
            chord3 = 17
        )
    ),
    GuitarNote(
        note = "D",
        octave = 5,
        positions = GuitarPositions(
            chord1 = 10,
            chord2 = 15,
            chord3 = 19
        )
    ),
    GuitarNote(
        note = "E",
        octave = 5,
        positions = GuitarPositions(
            chord1 = 12,
            chord2 = 17
        )
    ),
    GuitarNote(
        note = "F",
        octave = 5,
        positions = GuitarPositions(
            chord1 = 13,
            chord2 = 18
        )
    ),
    GuitarNote(
        note = "G",
        octave = 5,
        positions = GuitarPositions(
            chord1 = 15,
            chord2 = 20
        )
    ),
    GuitarNote(
        note = "A",
        octave = 5,
        positions = GuitarPositions(
            chord1 = 17
        )
    ),
    GuitarNote(
        note = "B",
        octave = 5,
        positions = GuitarPositions(
            chord1 = 19
        )
    ),
    GuitarNote(
        note = "C",
        octave = 6,
        positions = GuitarPositions(
            chord1 = 20
        )
    )
)

data class GuitarPositions(
    val chord1: Int? = null,
    val chord2: Int? = null,
    val chord3: Int? = null,
    val chord4: Int? = null,
    val chord5: Int? = null,
    val chord6: Int? = null
) {
    fun asList() = listOf(chord1, chord2, chord3, chord4, chord5, chord6)
}