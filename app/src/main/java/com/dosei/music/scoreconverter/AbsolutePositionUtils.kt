package com.dosei.music.scoreconverter

fun findClosestNaturalNote(absolutePosition: Int): OctavedNote {
    val octave = absolutePosition / SCALE_SIZE
    val noteIndex = absolutePosition - (octave * SCALE_SIZE)
    var note = Note.withIndex(noteIndex)
    if (notFoundSharpNote(note)) {
        val naturalNoteIndex = noteIndex.dec()
        note = Note.withIndex(naturalNoteIndex)
    }
    note ?: throw IllegalStateException("Note not found")
    return OctavedNote(note, octave)
}

private fun notFoundSharpNote(note: Note?) = note == null