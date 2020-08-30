package com.dosei.music.scoreconverter.converter

import com.dosei.music.scoreconverter.NoteModifier
import com.dosei.music.scoreconverter.ui.view.ScoreNoteDecoration

fun NoteModifier?.toNoteDecoration(): ScoreNoteDecoration? = when (this) {
    NoteModifier.FLAT -> ScoreNoteDecoration.FLAT
    NoteModifier.SHARP -> ScoreNoteDecoration.SHARP
    else -> null
}

fun ScoreNoteDecoration.toNoteModifier(): NoteModifier? = when (this) {
    ScoreNoteDecoration.SHARP -> NoteModifier.SHARP
    ScoreNoteDecoration.FLAT -> NoteModifier.FLAT
    else -> null
}
