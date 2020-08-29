package com.dosei.music.scoreconverter

class NoteRepository {

    fun findAllNotesRange(): IntRange {
        val firstNotePosition = Guitar.default().tuning.string6.noteRange.first
        val lastNotePosition = Guitar.default().tuning.string1.noteRange.last
        return IntRange(firstNotePosition, lastNotePosition)
    }

    fun notePositions(absolutePosition: Int): GuitarPositions {
        val tuning = Guitar.default().tuning
        return GuitarPositions(
            string1 = positionAtString(tuning.string1, absolutePosition),
            string2 = positionAtString(tuning.string2, absolutePosition),
            string3 = positionAtString(tuning.string3, absolutePosition),
            string4 = positionAtString(tuning.string4, absolutePosition),
            string5 = positionAtString(tuning.string5, absolutePosition),
            string6 = positionAtString(tuning.string6, absolutePosition)
        )
    }

    private fun positionAtString(string: GuitarString, absolutePosition: Int): Int? {
        return if (string.noteRange.contains(absolutePosition)) {
            absolutePosition - string.noteRange.first
        } else {
            null
        }
    }
}