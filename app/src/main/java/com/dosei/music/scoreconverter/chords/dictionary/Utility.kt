package com.dosei.music.scoreconverter.chords.dictionary

import com.dosei.music.arpeggio.*

/**
 * Index finger position
 */
fun i(string: Int, fret: Int) = Position(fret, string, Finger.Index)

/**
 * Index finger barre
 */
fun i(strings: IntRange, fret: Int) = Barre(fret, strings, Finger.Index)

/**
 * Middle finger position
 */
fun m(string: Int, fret: Int) = Position(fret, string, Finger.Middle)

/**
 * Middle finger barre
 */
fun m(strings: IntRange, fret: Int) = Barre(fret, strings, Finger.Middle)

/**
 * Ring finger position
 */
fun r(string: Int, fret: Int) = Position(fret, string, Finger.Ring)

/**
 * Ring finger barre
 */
fun r(strings: IntRange, fret: Int) = Barre(fret, strings, Finger.Ring)

/**
 * Pinky finger position
 */
fun p(string: Int, fret: Int) = Position(fret, string, Finger.Pinky)

/**
 * Pinky finger barre
 */
fun p(strings: IntRange, fret: Int) = Barre(fret, strings, Finger.Pinky)

/**
 * Open string
 */
fun o(string: Int) = OpenString(string)