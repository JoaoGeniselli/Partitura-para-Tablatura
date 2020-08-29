package com.dosei.music.scoreconverter

class CalculateGuitarRangeUseCase {

    fun calculateGeneralRange(guitar: Guitar) : IntRange {
        return IntRange(
            start = guitar.tuning.string6.noteRange.first,
            endInclusive = guitar.tuning.string1.noteRange.last
        )
    }

    fun generateAllNotes(guitar: Guitar): List<OctavedNote> {
        val generalRange = calculateGeneralRange(guitar)
        var currentNote = guitar.tuning.string6.initialNote
        val allNotes = mutableListOf<OctavedNote>()
        while (currentNote.absolutePosition <= generalRange.last) {
            allNotes.add(currentNote)
            currentNote = currentNote.nextNatural
        }
        return allNotes
    }
}