package com.dosei.music.scoreconverter

data class GuitarNote(
    val name: String,
    val positions: GuitarPositions
)

fun initNaturalNotes(): List<GuitarNote> = listOf(
    GuitarNote("E2", GuitarPositions(
        chord6 = 0
    )),
    GuitarNote("F2", GuitarPositions(
        chord6 = 1
    )),
    GuitarNote("G2", GuitarPositions(
        chord6 = 3
    )),
    GuitarNote("A2", GuitarPositions(
        chord5 = 0,
        chord6 = 5
    )),
    GuitarNote("B2", GuitarPositions(
        chord5 = 2,
        chord6 = 7
    )),
    GuitarNote("C3", GuitarPositions(
        chord5 = 3,
        chord6 = 8
    )),
    GuitarNote("D3", GuitarPositions(
        chord4 = 0,
        chord5 = 5,
        chord6 = 10
    )),
    GuitarNote("E3", GuitarPositions(
        chord4 = 2,
        chord5 = 7,
        chord6 = 12
    )),
    GuitarNote("F3", GuitarPositions(
        chord4 = 3,
        chord5 = 8,
        chord6 = 13
    )),
    GuitarNote("G3", GuitarPositions(
        chord3 = 0,
        chord4 = 5,
        chord5 = 10,
        chord6 = 15
    )),
    GuitarNote("A3", GuitarPositions(
        chord3 = 2,
        chord4 = 7,
        chord5 = 12,
        chord6 = 17
    )),
    GuitarNote("B3", GuitarPositions(
        chord2 = 0,
        chord3 = 4,
        chord4 = 9,
        chord5 = 14,
        chord6 = 19
    )),
    GuitarNote("C4", GuitarPositions(
        chord2 = 1,
        chord3 = 5,
        chord4 = 10,
        chord5 = 15,
        chord6 = 20
    )),
    GuitarNote("D4", GuitarPositions(
        chord2 = 3,
        chord3 = 7,
        chord4 = 12,
        chord5 = 17
    )),
    GuitarNote("E4", GuitarPositions(
        chord1 = 0,
        chord2 = 5,
        chord3 = 9,
        chord4 = 14,
        chord5 = 19
    )),
    GuitarNote("F4", GuitarPositions(
        chord1 = 1,
        chord2 = 6,
        chord3 = 10,
        chord4 = 15,
        chord5 = 20
    )),
    GuitarNote("G4", GuitarPositions(
        chord1 = 3,
        chord2 = 8,
        chord3 = 12,
        chord4 = 17
    )),
    GuitarNote("A4", GuitarPositions(
        chord1 = 5,
        chord2 = 10,
        chord3 = 14,
        chord4 = 19
    )),
    GuitarNote("B4", GuitarPositions(
        chord1 = 7,
        chord2 = 12,
        chord3 = 16
    )),
    GuitarNote("C5", GuitarPositions(
        chord1 = 8,
        chord2 = 13,
        chord3 = 17
    )),
    GuitarNote("D5", GuitarPositions(
        chord1 = 10,
        chord2 = 15,
        chord3 = 19
    )),
    GuitarNote("E5", GuitarPositions(
        chord1 = 12,
        chord2 = 17
    )),
    GuitarNote("F5", GuitarPositions(
        chord1 = 13,
        chord2 = 18
    )),
    GuitarNote("G5", GuitarPositions(
        chord1 = 15,
        chord2 = 20
    )),
    GuitarNote("A5", GuitarPositions(
        chord1 = 17
    )),
    GuitarNote("B5", GuitarPositions(
        chord1 = 19
    )),
    GuitarNote("C6", GuitarPositions(
        chord1 = 20
    ))
)

data class GuitarPositions(
    val chord1: Int? = null,
    val chord2: Int? = null,
    val chord3: Int? = null,
    val chord4: Int? = null,
    val chord5: Int? = null,
    val chord6: Int? = null
)