package com.dosei.music.scoreconverter

data class GuitarString(
    val initialNote: OctavedNote
) {
    val noteRange: IntRange

    init {
        val firstPosition = initialNote.absoluteSemitonePosition
        noteRange = IntRange(
            start = firstPosition,
            endInclusive = firstPosition + Guitar.fretsAmount
        )
    }
}