package com.dosei.music.scoreconverter.domain

data class OctavedNote(
    val note: Note,
    val octave: Int,
    var modifier: NoteModifier? = null
) {
    val name: String = "${note.name}${modifier?.noteSuffix.orEmpty()}$octave"
    val absolutePosition: Int
        get() = note.scalePosition + (modifier?.absolutePositionDiff
            ?: 0) + (Note.SCALE_SIZE * octave)

    val nextNatural: OctavedNote
        get() {
            val nextNote = note.next
            val nextNoteOctave = if (note.isOctaveEnd()) octave.inc() else octave
            return OctavedNote(
                note = nextNote,
                octave = nextNoteOctave
            )
        }

    override fun toString(): String {
        return note.name + modifier?.noteSuffix.orEmpty() + octave.toString()
    }
}