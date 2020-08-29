package com.dosei.music.scoreconverter

const val SCALE_SIZE = 12

enum class Note {
    C {
        override val scalePosition: Int = 0
        override val next: Note by lazy { D }
    },
    D {
        override val scalePosition: Int = 2
        override val next: Note by lazy { E }
    },
    E {
        override val scalePosition: Int = 4
        override val next: Note by lazy { F }
    },
    F {
        override val scalePosition: Int = 5
        override val next: Note by lazy { G }
    },
    G {
        override val scalePosition: Int = 7
        override val next: Note by lazy { A }
    },
    A {
        override val scalePosition: Int = 9
        override val next: Note by lazy { B }
    },
    B {
        override val scalePosition: Int = 11
        override val next: Note by lazy { C }
    };

    abstract val scalePosition: Int
    abstract val next: Note

    fun isOctaveStart() = this == C

    fun isOctaveEnd() = this == B

    companion object {

        fun withIndex(index: Int): Note? {
            return values().firstOrNull { it.scalePosition == index }
        }
    }
}