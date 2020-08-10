package com.dosei.music.scoreconverter

data class OctavedNote(
    val note: Note,
    val octave: Int,
    val type: NoteType = NoteType.NATURAL
) {
    val name: String = "${note.name}${type.noteSuffix}$octave"
    val absoluteSemitonePosition: Int get() = note.scalePosition + type.absolutePositionDiff + (SCALE_SIZE * octave)
}