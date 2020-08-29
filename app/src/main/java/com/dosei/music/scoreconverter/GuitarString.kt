package com.dosei.music.scoreconverter

data class GuitarString(
    val initialNote: OctavedNote,
    val frets: Int = 20
) {
    val noteRange: IntRange

    init {
        val firstPosition = initialNote.absolutePosition
        noteRange = IntRange(
            start = firstPosition,
            endInclusive = firstPosition + frets
        )
    }
}