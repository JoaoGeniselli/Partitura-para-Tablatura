package com.dosei.music.scoreconverter.chords.dictionary

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.Barre
import com.dosei.music.arpeggio.Component
import com.dosei.music.arpeggio.OpenString
import com.dosei.music.arpeggio.Position

object Chords {

    val A = listOf(
        chord("A", Shapes.C(12)),
        chord(
            "A",
            o(5),
            i(4, 2),
            m(3, 2),
            r(2, 2),
            o(1)
        ),
        chord("A", Shapes.G(5)),
        chord("A", Shapes.E(5)),
        chord("A", Shapes.D(7)),
    )

    val B = listOf(
        chord("B", Shapes.C(14)),
        chord("B", Shapes.A(2)),
        chord("B", Shapes.G(7)),
        chord("B", Shapes.E(7)),
        chord("B", Shapes.D(9)),
    )

    val C = listOf(
        chord(
            "C",
            r(5, 3),
            m(4, 2),
            o(3),
            i(2, 1),
            o(1)
        ),
        chord("C", Shapes.A(3)),
        chord("C", Shapes.G(8)),
        chord("C", Shapes.E(8)),
        chord("C", Shapes.D(10)),
    )

    val D = listOf(
        chord("D", Shapes.C(5)),
        chord("D", Shapes.A(5)),
        chord("D", Shapes.G(10)),
        chord("D", Shapes.E(10)),
        chord(
            "D",
            o(4),
            i(3, 2),
            r(2, 3),
            m(1, 2)
        )
    )

    val E = listOf(
        chord("E", Shapes.C(7)),
        chord("E", Shapes.A(7)),
        chord("E", Shapes.G(12)),
        chord(
            "E",
            o(6),
            m(5, 2),
            r(4, 2),
            i(3, 1),
            o(2),
            o(1),
        ),
        chord("E", Shapes.D(2))
    )

    val F = listOf(
        chord("F", Shapes.C(8)),
        chord("F", Shapes.A(8)),
        chord("F", Shapes.G(13)),
        chord("F", Shapes.E(1)),
        chord("F", Shapes.D(3))
    )

    val G = listOf(
        chord("G", Shapes.C(10)),
        chord("G", Shapes.A(10)),
        chord(
            "G",
            m(6, 3),
            i(5, 2),
            o(4),
            o(3),
            o(2),
            r(1, 3)
        ),
        chord("G", Shapes.E(3)),
        chord("G", Shapes.D(5))
    )

    val Am = chord(
        "Am",
        o(5),
        m(4, 2),
        r(3, 2),
        i(2, 1),
        o(1)
    )

    val Bm = chord("Bm", Shapes.Am(2))

    val Cm = chord("Cm", Shapes.Am(3))

    val Dm = chord(
        "Dm",
        o(4),
        m(3, 2),
        r(2, 3),
        i(1, 1)
    )

    val Em = chord(
        "Em",
        o(6),
        m(5, 2),
        r(4, 2),
        o(3),
        o(2),
        o(1)
    )

    val Fm = chord("Fm", Shapes.Em(1))

    val Gm = chord("Gm", Shapes.Em(3))

    val A7 = chord(
        "A7",
        o(5),
        m(4, 2),
        o(3),
        r(2, 2),
        o(1)
    )

    val B7 = chord(
        "B7",
        m(5, 2),
        i(4, 1),
        r(3, 2),
        o(2),
        p(1, 2),
    )

    val C7 = chord(
        "C7",
        r(5, 3),
        m(4, 2),
        p(3, 3),
        i(2, 1),
        o(1),
    )

    val D7 = chord(
        "D7",
        o(4),
        m(3, 2),
        i(2, 1),
        r(1, 2),
    )

    val E7 = chord(
        "E7",
        o(6),
        m(5, 2),
        r(4, 2),
        i(3, 1),
        p(2, 3),
        o(1),
    )

    val F7 = chord(
        "F7",
        i(1..6, 1),
        r(5, 3),
        m(3, 2),
        p(2, 4),
    )

    val G7 = chord(
        "G7",
        r(6, 3),
        m(5, 2),
        o(4),
        o(3),
        o(2),
        i(1, 1),
    )

    val ADim = chord(
        "Aº",
        o(5),
        i(4, 1),
        r(3, 2),
        m(2, 1),
    )

    val BDim = chord(
        "Bº",
        m(5, 2),
        r(4, 3),
        i(3, 1),
        p(2, 3),
    )

    val CDim = chord(
        "Cº",
        m(5, 3),
        r(4, 4),
        i(3, 2),
        p(2, 4),
    )

    val DDim = chord(
        "Dº",
        o(4),
        i(3, 1),
        o(2),
        m(1, 1),
    )

    val EDim = chord(
        "Eº",
        i(4, 2),
        r(3, 3),
        m(2, 2),
        p(1, 3),
    )

    val FDim = chord(
        "Fº",
        i(6, 1),
        o(4),
        m(3, 1),
        o(2),
    )

    val GDim = chord(
        "Gº",
        i(4, 5),
        r(3, 6),
        m(2, 5),
        p(1, 6),
    )

    val all = (A + B + C + D + E + F + G + listOf(
        Am, Bm, Cm, Dm, Em, Fm, Gm,
        A7, B7, C7, D7, E7, F7, G7,
        ADim, BDim, CDim, DDim, EDim, FDim, GDim,

        // _2, _3, _4, _5,
    )).sortedBy { it.firstFret }

    val Chord.firstFret: Int
        get() = components.minOf {
            when (it) {
                is Position -> it.fret
                is Barre -> it.fret
                else -> 0
            }
        }

    private fun chord(name: AnnotatedString, vararg components: Component): Chord {
        return Chord(name = name, components = components.toList())
    }

    private fun chord(name: String, vararg components: Component): Chord {
        return chord(name = AnnotatedString(name), components = components)
    }

    private fun chord(name: String, components: List<Component>): Chord {
        return Chord(name = AnnotatedString(name), components = components)
    }
}
