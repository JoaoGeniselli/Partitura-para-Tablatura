package com.dosei.music.scoreconverter.chords.dictionary

import com.dosei.music.arpeggio.*

object Shapes {

    // region Major Shapes

    fun C(tonic: Int): List<Component> = listOf(
        b(tonic - 3, 1..3, 1),
        p(5, tonic, 4),
        p(4, tonic - 1, 3),
        p(2, tonic - 2, 2)
    )

    fun A(tonic: Int): List<Component> = listOf(
        p(4, tonic + 2, 2),
        p(3, tonic + 2, 3),
        p(2, tonic + 2, 4),
        b(tonic, 1..5, 1)
    )

    fun G(tonic: Int): List<Component> = listOf(
        b(tonic - 3, 1..6, 1),
        p(6, tonic, 3),
        p(5, tonic - 1, 2),
        p(1, tonic, 4),
    )

    fun E(tonic: Int): List<Component> = listOf(
        b(tonic, 1..6, 1),
        p(5, tonic + 2, 3),
        p(4, tonic + 2, 4),
        p(3, tonic + 1, 2),
    )

    fun D(tonic: Int): List<Component> = listOf(
        p(4, tonic, 1),
        p(3, tonic + 2, 2),
        p(2, tonic + 3, 4),
        p(1, tonic + 2, 3),
    )

    // endregion

    // region Minor Shapes

    fun Am(tonic: Int): List<Component> = listOf(
        b(tonic, 1..5, 1),
        p(4, tonic + 2, 3),
        p(3, tonic + 2, 4),
        p(2, tonic + 1, 2),
    )

    fun Em(tonic: Int): List<Component> = listOf(
        b(tonic, 1..6, 1),
        p(5, tonic + 2, 3),
        p(4, tonic + 2, 4),
    )

    fun Dm(tonic: Int): List<Component> = listOf(
        p(4, tonic, 1),
        p(3, tonic + 2, 3),
        p(2, tonic + 3, 4),
        p(1, tonic + 1, 2),
    )

    // endregion

}