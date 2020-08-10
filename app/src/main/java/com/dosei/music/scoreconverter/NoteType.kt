package com.dosei.music.scoreconverter

enum class NoteType(val noteSuffix: String, val absolutePositionDiff: Int) {
    NATURAL("", 0),
    SHARP("#", 1),
    FLAT("b", -1)
}