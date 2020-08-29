package com.dosei.music.scoreconverter.domain

data class GuitarString(
    val initialNote: OctavedNote,
    val frets: Int = 20
) {
    val noteRange: IntRange

    fun positionOf(note: OctavedNote): Int? {
        val notePosition = note.absolutePosition
        if (noteRange.contains(notePosition)) {
            return notePosition - initialNote.absolutePosition
        }
        return null
    }

    init {
        val firstPosition = initialNote.absolutePosition
        noteRange = IntRange(
            start = firstPosition,
            endInclusive = firstPosition + frets
        )
    }
}