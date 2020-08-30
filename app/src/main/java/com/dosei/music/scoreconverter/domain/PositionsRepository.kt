package com.dosei.music.scoreconverter.domain

import com.dosei.music.scoreconverter.ui.view.GuitarPositions

class PositionsRepository {

    fun findPositions(note: OctavedNote, guitar: Guitar) = guitar.tuning.run {
        GuitarPositions(
            string1 = string1.positionOf(note),
            string2 = string2.positionOf(note),
            string3 = string3.positionOf(note),
            string4 = string4.positionOf(note),
            string5 = string5.positionOf(note),
            string6 = string6.positionOf(note)
        )
    }
}