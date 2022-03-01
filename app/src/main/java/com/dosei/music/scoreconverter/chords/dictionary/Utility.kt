package com.dosei.music.scoreconverter.chords.dictionary

import com.dosei.music.arpeggio.*

fun p(string: Int, fret: Int = 0, finger: Int? = null): Component =
    if (fret == 0) {
        OpenString(string)
    } else {
        Position(
            fret = fret,
            string = string,
            finger = fingerFromIndex(finger)
        )
    }

fun o(string: Int) = OpenString(string)

fun b(fret: Int, strings: IntRange, finger: Int? = null) =
    Barre(
        fret = fret,
        strings = strings,
        finger = fingerFromIndex(finger)
    )

fun fingerFromIndex(index: Int?): Finger? =
    when (index) {
        1 -> Finger.Index
        2 -> Finger.Middle
        3 -> Finger.Ring
        4 -> Finger.Pinky
        else -> null
    }