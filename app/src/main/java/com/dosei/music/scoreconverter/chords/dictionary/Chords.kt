package com.dosei.music.scoreconverter.chords.dictionary

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import com.dosei.music.arpeggio.*

object Chords {

    // region Major Forms

    private fun cForm(tonic: Int): List<Component> = listOf(
        p(5, tonic, 4),
        p(4, tonic - 1, 3),
        p(2, tonic - 2, 2),
        b(tonic - 3, 1..3, 1)
    )

    private fun aForm(tonic: Int): List<Component> = listOf(
        p(4, tonic + 2, 2),
        p(3, tonic + 2, 3),
        p(2, tonic + 2, 4),
        b(tonic, 1..5, 1)
    )

    private fun gForm(tonicFret: Int): List<Component> {
        val components = mutableListOf<Component>(
            Position(fret = tonicFret, string = 6, finger = Finger.Ring),
            Position(fret = tonicFret - 1, string = 5, finger = Finger.Middle),
            Position(fret = tonicFret, string = 1, finger = Finger.Pinky)
        )
        if (tonicFret - 3 == 0) {
            components.run {
                add(OpenString(4))
                add(OpenString(3))
                add(OpenString(2))
            }
        } else {
            components.add(Barre(fret = tonicFret - 3, finger = Finger.Index, strings = 1..6))
        }
        return components
    }

    private fun eForm(tonicFret: Int): List<Component> {
        val components = mutableListOf<Component>(
            Position(fret = tonicFret + 2, string = 5, finger = Finger.Ring),
            Position(fret = tonicFret + 2, string = 4, finger = Finger.Pinky),
            Position(fret = tonicFret + 1, string = 3, finger = Finger.Middle)
        )
        if (tonicFret == 0) {
            components.run {
                add(OpenString(1))
                add(OpenString(6))
            }
        } else {
            components.add(Barre(fret = tonicFret, finger = Finger.Index, strings = 1..6))
        }
        return components
    }

    private fun dForm(tonic: Int): List<Component> {
        listOf(
            p(3, tonic + 2, 2),
            p(3, tonic + 2, 4),
        )
        val components = mutableListOf<Component>(
            Position(fret = tonic + 2, string = 3, finger = Finger.Middle),
            Position(fret = tonic + 3, string = 2, finger = Finger.Pinky),
            Position(fret = tonic + 2, string = 1, finger = Finger.Ring),
        )
        if (tonic == 0) {
            components.add(OpenString(4))
        } else {
            components.add(Barre(fret = tonic, strings = 1..4, finger = Finger.Index))
        }
        return components
    }

    // endregion

    // region Minor Forms

    private fun p(string: Int, fret: Int = 0, finger: Int? = null): Component =
        if (fret == 0) {
            OpenString(string)
        } else {
            Position(
                fret = fret,
                string = string,
                finger = fingerFromIndex(finger)
            )
        }

    private fun o(string: Int) = OpenString(string)

    private fun b(fret: Int, strings: IntRange, finger: Int? = null) =
        Barre(
            fret = fret,
            strings = strings,
            finger = fingerFromIndex(finger)
        )

    private fun amForm(tonic: Int): List<Component> {
        val components = listOf(
            p(4, tonic + 2, 3),
            p(3, tonic + 2, 4),
            p(2, tonic + 1, 2)
        )
        return if (tonic == 0) {
            components + o(5) + o(1)
        } else {
            components + b(tonic, 1..5, 1)
        }
    }

    private fun fingerFromIndex(index: Int?): Finger? =
        when (index) {
            1 -> Finger.Index
            2 -> Finger.Middle
            3 -> Finger.Ring
            4 -> Finger.Pinky
            else -> null
        }

    private fun emForm(tonicFret: Int): List<Component> {
        val components = mutableListOf<Component>(
            Position(fret = tonicFret + 2, string = 5, finger = Finger.Middle),
            Position(fret = tonicFret + 2, string = 4, finger = Finger.Ring),
        )
        if (tonicFret == 0) {
            components.run {
                add(OpenString(6))
                add(OpenString(3))
                add(OpenString(2))
                add(OpenString(1))
            }
        } else {
            components.add(Barre(fret = tonicFret, finger = Finger.Index, strings = 1..6))
        }
        return components
    }

    private fun dmForm(tonicFret: Int): List<Component> {
        val components = mutableListOf<Component>(
            Position(fret = tonicFret + 2, string = 3, finger = Finger.Ring),
            Position(fret = tonicFret + 3, string = 2, finger = Finger.Pinky),
            Position(fret = tonicFret + 1, string = 1, finger = Finger.Middle),
        )
        if (tonicFret == 0) {
            components.add(OpenString(4))
        } else {
            components.add(Barre(fret = tonicFret, strings = 1..4, finger = Finger.Index))
        }
        return components
    }

    // endregion

    val A = chord(
        "A",
        p(5),
        p(4, 2, 1),
        p(3, 2, 2),
        p(2, 2, 3),
        p(1)
    )

    val B = Chord(
        name = AnnotatedString("B"),
        components = aForm(2)
    )

    val C = chord(
        "C",
        p(5, 3, 3),
        p(4, 2, 2),
        p(3),
        p(2, 1, 1),
        p(1)
    )

    val D = chord(
        "D",
        p(4),
        p(3, 2, 1),
        p(2, 3, 3),
        p(1, 2, 2)
    )

    val E = chord(
        "E",
        p(6),
        p(5, 2, 2),
        p(4, 2, 3),
        p(3),
        p(2),
        p(1),
    )

    private fun chord(name: AnnotatedString, vararg components: Component): Chord {
        return Chord(name = name, components = components.toList())
    }

    private fun chord(name: String, vararg components: Component): Chord {
        return chord(name = AnnotatedString(name), components = components)
    }

    val F = Chord(
        name = AnnotatedString("F"),
        components = eForm(1)
    )

    val G = chord(
        "G",
        p(6, 3, 2),
        p(5, 2, 2),
        p(4),
        p(3),
        p(2),
        p(1, 3, 3)
    )

    val Am = chord(
        "Am",
        p(5),
        p(4, 2, 2),
        p(3, 2, 3),
        p(2, 1, 1),
        p(1)
    )

    val Bm = Chord(
        name = AnnotatedString("Bm"),
        components = amForm(2)
    )

    val Cm = Chord(
        name = AnnotatedString("Cm"),
        components = amForm(3)
    )

    val Dm = chord(
        "Dm",
        p(4),
        p(3, 2, 2),
        p(2, 3, 3),
        p(1, 1, 1)
    )

    val Em = chord(
        "Em",
        p(6),
        p(5, 2, 2),
        p(4, 2, 3),
        p(3),
        p(2),
        p(1)
    )

    val Fm = Chord(
        name = AnnotatedString("Fm"),
        components = emForm(1)
    )

    val Gm = Chord(
        name = AnnotatedString("Gm"),
        components = emForm(3)
    )

    val A7 = chord(
        "A7",
        p(5),
        p(4, 2, 2),
        p(3),
        p(2, 2, 3),
        p(1)
    )

    val B7 = chord(
        "B7",
        p(5, 2, 2),
        p(4, 1, 1),
        p(3, 2, 3),
        p(2),
        p(1, 2, 4),
    )

    val C7 = chord(
        "C7",
        p(5, 3, 3),
        p(4, 2, 2),
        p(3, 3, 4),
        p(2, 1, 1),
        p(1),
    )

    val D7 = chord(
        "D7",
        p(4),
        p(3, 2, 2),
        p(2, 1, 1),
        p(1, 2, 3),
    )

    val E7 = chord(
        "E7",
        p(6),
        p(5, 2, 2),
        p(4, 2, 3),
        p(3, 1, 1),
        p(2, 3, 4),
        p(1),
    )

    val F7 = chord(
        "F7",
        b(1, 1..6, 1),
        p(5, 3, 5),
        p(3, 2, 2),
        p(2, 4, 4),
    )

    val G7 = chord(
        "G7",
        p(6, 3, 3),
        p(5, 2, 2),
        p(4),
        p(3),
        p(2),
        p(1, 1, 1),
    )

    val ADim = chord(
        "Aº",
        p(5),
        p(4, 1, 1),
        p(3, 2, 3),
        p(2, 1, 2),
    )

    val BDim = chord(
        "Bº",
        p(5, 2, 2),
        p(4, 3, 3),
        p(3, 1, 1),
        p(2, 3, 4),
    )

    val CDim = chord(
        "Cº",
        p(5, 3, 2),
        p(4, 4, 3),
        p(3, 2, 1),
        p(2, 4, 4),
    )

    val DDim = chord(
        "Dº",
        p(4),
        p(3, 1, 1),
        p(2),
        p(1, 1, 2),
    )

    val EDim = chord(
        "Eº",
        p(4, 2, 1),
        p(3, 3, 3),
        p(2, 2, 2),
        p(1, 3, 4),
    )

    val FDim = chord(
        "Fº",
        p(6, 1, 1),
        p(4),
        p(3, 1, 2),
        p(2),
    )

    val GDim = chord(
        "Gº",
        p(4, 5, 1),
        p(3, 6, 3),
        p(2, 5, 2),
        p(1, 6, 4),
    )

    val all = listOf(
        A, B, C, D, E, F, G,
        Am, Bm, Cm, Dm, Em, Fm, Gm,
        A7, B7, C7, D7, E7, F7, G7,
        ADim, BDim, CDim, DDim, EDim, FDim, GDim
    )
}
