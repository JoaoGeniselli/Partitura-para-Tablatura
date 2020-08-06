package com.dosei.chordsdictionary

enum class GuitarNotes {
    C2,
    C2_SHARP,
    D2_FLAT,
    D2,
    D2_SHARP,
    E2,
    F2,
    F2_SHARP,
    G2_FLAT,
    G2,
    G2_SHARP,
    A2_FLAT,
    A2,
    A2_SHARP,
    B2_FLAT,
    C3,
    C3_SHARP,
    D3_FLAT,
    D3,
    D3_SHARP,
    E3,
    F3,
    F3_SHARP,
    G3_FLAT,
    G3,
    G3_SHARP,
    A3_FLAT,
    A3,
    A3_SHARP,
    B3_FLAT,
    C4,
    C4_SHARP,
    D4_FLAT,
    D4,
    D4_SHARP,
    E4,
    F4,
    F4_SHARP,
    G4_FLAT,
    G4,
    G4_SHARP,
    A4_FLAT,
    A4,
    A4_SHARP,
    B4_FLAT,
    C5,
    C5_SHARP,
    D5_FLAT,
    D5,
    D5_SHARP,
    E5,
    F5,
    F5_SHARP,
    G5_FLAT,
    G5,
    G5_SHARP,
    A5_FLAT,
    A5,
    A5_SHARP,
    B5_FLAT,
    C6,
    C6_SHARP,
    D6_FLAT,
    D6,
    D6_SHARP,
    E6
}

object TrebleClef {
    private const val highLineLimitPosition = 8
    private const val lowLineLimitPosition = -2

    val sheetLineRange = IntRange(lowLineLimitPosition, highLineLimitPosition)
}

data class TrebleClefPosition(
    val positionsOverReferenceG: Int
) {
    fun hasAdditionalLine() = !TrebleClef.sheetLineRange.contains(positionsOverReferenceG)
}