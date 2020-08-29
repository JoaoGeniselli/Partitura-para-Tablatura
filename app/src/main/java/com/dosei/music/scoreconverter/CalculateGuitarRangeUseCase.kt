package com.dosei.music.scoreconverter

class CalculateGuitarRangeUseCase {

    fun calculateGeneralRange(guitar: Guitar) : IntRange {
        return IntRange(
            start = guitar.tuning.string6.noteRange.first,
            endInclusive = guitar.tuning.string1.noteRange.last
        )
    }

//    fun allGuitarNotes(guitar: Guitar) {
//        val generalRange = calculateGeneralRange()
//    }
}