package com.dosei.music.scoreconverter.converter

import com.dosei.music.scoreconverter.domain.OctavedNote

class MIDINoteConverter {

    fun convert(octavedNote: OctavedNote) : Int {
        return octavedNote.absolutePosition + ABSOLUTE_POSITION_TO_MIDI_DIFF
    }

    companion object {
        private const val ABSOLUTE_POSITION_TO_MIDI_DIFF = 12
    }

}
