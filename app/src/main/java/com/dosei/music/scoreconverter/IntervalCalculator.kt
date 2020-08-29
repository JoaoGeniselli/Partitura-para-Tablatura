package com.dosei.music.scoreconverter

class IntervalCalculator {

    fun calculateSemitonesBetween(initialNote: OctavedNote, finalNote: OctavedNote): Int {
        return finalNote.absolutePosition - initialNote.absolutePosition
    }
}