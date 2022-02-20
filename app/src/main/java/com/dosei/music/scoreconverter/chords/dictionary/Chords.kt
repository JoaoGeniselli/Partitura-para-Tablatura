package com.dosei.music.scoreconverter.chords.dictionary

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.Barre
import com.dosei.music.arpeggio.Finger
import com.dosei.music.arpeggio.OpenString
import com.dosei.music.arpeggio.Position

object Chords {

    val A = Chord(
        name = AnnotatedString("A"),
        components = listOf(
            OpenString(5),
            OpenString(1),
            Position(fret = 2, string = 4, finger = Finger.Index),
            Position(fret = 2, string = 3, finger = Finger.Middle),
            Position(fret = 2, string = 2, finger = Finger.Ring),
        )
    )

    val B = Chord(
        name = AnnotatedString("B"),
        components = listOf(
            Barre(fret = 2, strings = 1..5, finger = Finger.Index),
            Position(fret = 4, string = 4, finger = Finger.Middle),
            Position(fret = 4, string = 3, finger = Finger.Ring),
            Position(fret = 4, string = 2, finger = Finger.Pinky),
        )
    )

    val C = Chord(
        name = AnnotatedString("C"),
        components = listOf(
            OpenString(3),
            OpenString(1),
            Position(fret = 3, string = 5, finger = Finger.Ring),
            Position(fret = 2, string = 4, finger = Finger.Middle),
            Position(fret = 1, string = 2, finger = Finger.Index),
        )
    )

}
