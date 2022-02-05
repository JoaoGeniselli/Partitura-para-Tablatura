package com.dosei.music.scoreconverter.ui.view

enum class NotationNotes(val index: Int, val isMainLine: Boolean, val isLine: Boolean, val tailInStart: Boolean, val tailIndex: Int, val supplementaryLines: List<Int>) {
    B6(index = 0,   isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10, 8, 6, 4, 2)),
    A6(index = 1,   isMainLine = false,  isLine = true,   tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10, 8, 6, 4, 2)),
    G6(index = 2,   isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10, 8, 6, 4)),
    F6(index = 3,   isMainLine = false,  isLine = true,   tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10, 8, 6, 4)),
    E6(index = 4,   isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10, 8, 6)),
    D6(index = 5,   isMainLine = false,  isLine = true,   tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10, 8, 6)),
    C6(index = 6,   isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10, 8)),
    B5(index = 7,   isMainLine = false,  isLine = true,   tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10, 8)),
    A5(index = 8,   isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10)),
    G5(index = 9,   isMainLine = false,  isLine = true,   tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12, 10)),
    F5(index = 10,  isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12)),
    E5(index = 11,  isMainLine = false,  isLine = true,   tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14, 12)),
    D5(index = 12,  isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14)),
    C5(index = 13,  isMainLine = false,  isLine = true,   tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16, 14)),
    B4(index = 14,  isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 22, supplementaryLines = listOf(16)),
    A4(index = 15,  isMainLine = false,  isLine = true,   tailInStart = true,   tailIndex = 23, supplementaryLines = listOf(16)),
    G4(index = 16,  isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 24, supplementaryLines = listOf()),
    F4(index = 17,  isMainLine = true,   isLine = true,   tailInStart = true,   tailIndex = 25, supplementaryLines = listOf()),
    E4(index = 18,  isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 26, supplementaryLines = listOf()),
    D4(index = 19,  isMainLine = true,   isLine = true,   tailInStart = true,   tailIndex = 27, supplementaryLines = listOf()),
    C4(index = 20,  isMainLine = false,  isLine = false,  tailInStart = true,   tailIndex = 28, supplementaryLines = listOf()),
    B3(index = 21,  isMainLine = true,   isLine = true,   tailInStart = true,   tailIndex = 29, supplementaryLines = listOf()),
    A3(index = 22,  isMainLine = false,  isLine = false,  tailInStart = false,  tailIndex = 16, supplementaryLines = listOf()),
    G3(index = 23,  isMainLine = true,   isLine = true,   tailInStart = false,  tailIndex = 17, supplementaryLines = listOf()),
    F3(index = 24,  isMainLine = false,  isLine = false,  tailInStart = false,  tailIndex = 18, supplementaryLines = listOf()),
    E3(index = 25,  isMainLine = true,   isLine = true,   tailInStart = false,  tailIndex = 19, supplementaryLines = listOf()),
    D3(index = 26,  isMainLine = false,  isLine = false,  tailInStart = false,  tailIndex = 20, supplementaryLines = listOf()),
    C3(index = 27,  isMainLine = false,  isLine = true,   tailInStart = false,  tailIndex = 21, supplementaryLines = listOf(28)),
    B2(index = 28,  isMainLine = false,  isLine = false,  tailInStart = false,  tailIndex = 22, supplementaryLines = listOf(28)),
    A2(index = 29,  isMainLine = false,  isLine = true,   tailInStart = false,  tailIndex = 22, supplementaryLines = listOf(30, 28)),
    G2(index = 30,  isMainLine = false,  isLine = false,  tailInStart = false,  tailIndex = 22, supplementaryLines = listOf(30, 28)),
    F2(index = 31,  isMainLine = false,  isLine = true,   tailInStart = false,  tailIndex = 22, supplementaryLines = listOf(32, 30, 28)),
    E2(index = 32,  isMainLine = false,  isLine = false,  tailInStart = false,  tailIndex = 22, supplementaryLines = listOf(32, 30, 28));

    companion object {

        fun getAll(intRange: IntRange): List<NotationNotes> {
            return values().filter { intRange.contains(it.index) }.sortedBy { it.index }
        }

        fun getByIndex(noteIndex: Int): NotationNotes? =
            values().firstOrNull { it.index == noteIndex }
    }
}