package com.dosei.music.scoreconverter.chords.dictionary

import com.dosei.music.arpeggio.*

object Shapes {

    // region Major Shapes

    fun C(tonic: Int): List<Component> = listOf(
        i(1..3, tonic - 3),
        p(5, tonic),
        r(4, tonic - 1),
        m(2, tonic - 2)
    )

    fun A(tonic: Int): List<Component> = listOf(
        i(1..5, tonic),
        m(4, tonic + 2),
        r(3, tonic + 2),
        p(2, tonic + 2)
    )

    fun G(tonic: Int): List<Component> = listOf(
        i(1..6, tonic - 3),
        r(6, tonic),
        m(5, tonic - 1),
        p(1, tonic),
    )

    fun E(tonic: Int): List<Component> = listOf(
        i(1..6, tonic),
        r(5, tonic + 2),
        p(4, tonic + 2),
        m(3, tonic + 1),
    )

    fun D(tonic: Int): List<Component> = listOf(
        i(4, tonic),
        m(3, tonic + 2),
        p(2, tonic + 3),
        r(1, tonic + 2),
    )

    // endregion

    // region Minor Shapes

    fun Am(tonic: Int): List<Component> = listOf(
        i(1..5, tonic),
        r(4, tonic + 2),
        p(3, tonic + 2),
        m(2, tonic + 1),
    )

    fun Em(tonic: Int): List<Component> = listOf(
        i(1..6, tonic),
        r(5, tonic + 2),
        p(4, tonic + 2),
    )

    fun Dm(tonic: Int): List<Component> = listOf(
        i(4, tonic),
        r(3, tonic + 2),
        p(2, tonic + 3),
        m(1, tonic + 1),
    )

    // endregion

}