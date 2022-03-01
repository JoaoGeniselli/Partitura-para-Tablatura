package com.dosei.music.scoreconverter.chords.dictionary

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.Component

object Chords {

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
        components = Shapes.A(2)
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


    val F = Chord(
        name = AnnotatedString("F"),
        components = Shapes.E(1)
    )

    val G = chord(
        "G",
        p(6, 3, 2),
        p(5, 2, 1),
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
        components = Shapes.Am(2)
    )

    val Cm = Chord(
        name = AnnotatedString("Cm"),
        components = Shapes.Am(3)
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
        components = Shapes.Em(1)
    )

    val Gm = Chord(
        name = AnnotatedString("Gm"),
        components = Shapes.Em(3)
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
        p(5, 3, 3),
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

    private fun chord(name: AnnotatedString, vararg components: Component): Chord {
        return Chord(name = name, components = components.toList())
    }

    private fun chord(name: String, vararg components: Component): Chord {
        return chord(name = AnnotatedString(name), components = components)
    }
}
