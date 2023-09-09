package com.dosei.music.scoreconverter.screen.chords.dictionary.data

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.Barre
import com.dosei.music.arpeggio.Component
import com.dosei.music.arpeggio.Position
import com.dosei.music.scoreconverter.screen.chords.dictionary.Shapes
import com.dosei.music.scoreconverter.screen.chords.dictionary.i
import com.dosei.music.scoreconverter.screen.chords.dictionary.m
import com.dosei.music.scoreconverter.screen.chords.dictionary.o
import com.dosei.music.scoreconverter.screen.chords.dictionary.p
import com.dosei.music.scoreconverter.screen.chords.dictionary.r

object Chords {
    /*
    Tipos de acorde

    =============
    === MAIOR ===
    =============

    M (CAGED)
    M7+
    M7
    M/3ª
    M/5ª
    M(add9)
    M5+
    M6
    M6/5ª
    M69
    M69/5ª
    M69/3ª
    M7+/3ª
    M7+/5ª
    M7+(6)
    M7+(#5)
    M7+(#5)/3ª
    M7+(9)
    M7+(9)/3ª
    M7+(9)/5ª
    M7+(6 9)
    M7+(9)/7+ª
    M7+(#11)
    M7+(#11)/5ª
    M7+(9 #11)
    M7+(9 #11)/5ª
    M4
    M7/3ª
    M7/5ª
    M/7ª
    M7(b5)
    M7(b5)/3ª
    M7(#11)
    M7/(9 #11)
    M7(#11 13)
    M7(#5)
    M7(#5)/3ª
    M7(#5)/7ª
    M7(b13)
    M7(#5 9)
    M7(13)
    M7(9)
    M7(9 13)
    M7(b9)
    M7(#5 b9)
    M7(b9 b13)
    M7(b5 b9)
    M7(b9 b11)
    M7(b9 13)
    M7(#9)
    M7(#9)/5ª
    M7(#5 #9)
    M7(#9 #11)
    M7(b5 #9)
    M7 4
    M7 4(9)
    M7 4(13)
    M7 4(9 13)
    M7 4(b9)

    =============
    === MENOR ===
    =============

    m
    m7 ok
    m/3ª ok
    m/5ª ok
    m(add9) ok
    m6 ok
    m6/3ª ok
    m6/5ª ok
    m(6 9) ok
    m(6 9)/3ª ok
    m7/5ª ok
    m7(9) ok
    m7(9)/5ª ok
    m/7ª ok
    m7(b5)
    m7(11)
    m7(9 11)
    m(7+)
    m(6 7+) ok
    m(7+ 9)

    ================
    === DIMINUTO ===
    ================

    dim
    dim(b13)
    dim(7+)
   */

//    val A = listOf(
//        chord("A", Shapes.C(12)),
//        chord(
//            "A",
//            o(5),
//            i(4, 2),
//            m(3, 2),
//            r(2, 2),
//            o(1)
//        ),
//        chord("A", Shapes.G(5)),
//        chord("A", Shapes.E(5)),
//        chord("A", Shapes.D(7)),
//    )

    // region MAJOR

    val A = chord(
        "A",
        o(5),
        i(4, 2),
        m(3, 2),
        r(2, 2),
        o(1)
    )

    val B = chord("B", Shapes.A(2))

    val C = chord(
        "C",
        r(5, 3),
        m(4, 2),
        o(3),
        i(2, 1),
        o(1)
    )

    val D = chord(
        "D",
        o(4),
        i(3, 2),
        r(2, 3),
        m(1, 2)
    )

    val E = chord(
        "E",
        o(6),
        m(5, 2),
        r(4, 2),
        i(3, 1),
        o(2),
        o(1),
    )

    val F = chord("F", Shapes.E(1))

    val G = chord(
        "G",
        m(6, 3),
        i(5, 2),
        o(4),
        o(3),
        o(2),
        r(1, 3)
    )

    val Ash = chord("A#", Shapes.A(1))
    val Bsh = chord("B#", Shapes.A(3))
    val Csh = chord("C#", Shapes.A(4))
    val Dsh = chord("D#", Shapes.D(1))
    val Esh = chord("E#", Shapes.E(1))
    val Fsh = chord("F#", Shapes.E(2))
    val Gsh = chord("G#", Shapes.E(4))

    val Ab = chord("Ab", Shapes.E(4))
    val Bb = chord("Bb", Shapes.A(1))
    val Cb = chord("Cb", Shapes.A(2))
    val Db = chord("Db", Shapes.A(4))
    val Eb = chord("Eb", Shapes.A(6))
    val Fb = chord("Fb", Shapes.D(2))
    val Gb = chord("Gb", Shapes.E(2))

    // endregion

    // region MINOR

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

    val AmSh = chord("A#m", Shapes.Em(6))
    val BmSh = chord("B#m", Shapes.Am(3))
    val CmSh = chord("C#m", Shapes.Am(4))
    val DmSh = chord("D#m", Shapes.Am(6))
    val EmSh = chord("E#m", Shapes.Em(1))
    val FmSh = chord("F#m", Shapes.Em(2))
    val GmSh = chord("G#m", Shapes.Em(4))

    val Abm = chord("Abm", Shapes.Em(4))
    val Bbm = chord("Bbm", Shapes.Am(1))
    val Cbm = chord("Cbm", Shapes.Am(2))
    val Dbm = chord("Dbm", Shapes.Am(4))
    val Ebm = chord("Ebm", Shapes.Am(6))
    val Fbm = chord("Fbm", Shapes.Dm(2))
    val Gbm = chord("Gbm", Shapes.Em(2))

    // endregion

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

    val all = listOf(
        A, B, C, D, E, F, G,
        Ash, Bsh, Csh, Dsh, Esh, Fsh, Gsh,
        Ab, Bb, Cb, Db, Eb, Fb, Gb,
        Am, Bm, Cm, Dm, Em, Fm, Gm,
        AmSh, BmSh, CmSh, DmSh, EmSh, FmSh, GmSh,
        Abm, Bbm, Cbm, Dbm, Ebm, Fbm, Gbm,
    )

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
