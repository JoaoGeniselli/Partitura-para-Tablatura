package com.dosei.music.scoreconverter.ui.view

enum class NotationNotes(val index: Int, val isMainLine: Boolean, val isLine: Boolean) {
    B6(0, false, false),
    A6(1, false, true),
    G6(2, false, false),
    F6(3, false, true),
    E6(4, false, false),
    D6(5, false, true),
    C6(6, false, false),
    B5(7, false, true),
    A5(8, false, false),
    G5(9, false, true),
    F5(10, false, false),
    E5(11, false, true),
    D5(12, false, false),
    C5(13, false, true),
    B4(14, false, false),
    A4(15, false, true),
    G4(16, false, false),
    F4(17, true, true),
    E4(18, false, false),
    D4(19, true, true),
    C4(20, false, false),
    B3(21, true, true),
    A3(22, false, false),
    G3(23, true, true),
    F3(24, false, false),
    E3(25, true, true),
    D3(26, false, false),
    C3(27, false, true),
    B2(28, false, false),
    A2(29, false, true),
    G2(30, false, false),
    F2(31, false, true),
    E2(32, false, false);

    companion object {

        fun getAll(intRange: IntRange): List<NotationNotes> {
            return values().filter { intRange.contains(it.index) }.sortedBy { it.index }
        }
    }
}