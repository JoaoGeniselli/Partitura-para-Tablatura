package com.dosei.music.scoreconverter.feature.chords.dictionary

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.*
import com.dosei.music.scoreconverter.feature.chords.dictionary.data.Chord

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

    fun test() {

    }

    object Major {

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

        object Minor7 {

        }

        object Major7 {

            operator fun invoke() {

            }

            object Major6 {

            }
        }

        object Aug5 {

        }

        object Major6 {

        }
    }

    fun testMinor() {
//        Minor() // m
//        Minor.Minor7() // m7 ok
//        Minor.BassOn3() // m/3ª ok
//        Minor.BassOn5() // m/5ª ok
//        Minor.Add9() // m(add9) ok
//        Minor.Major6() // m6 ok
//        Minor.Major6.BassOn3() // m6/3ª ok
//        Minor.Major6.BassOn5() // m6/5ª ok
//        Minor.Major6.Major9() // m(6 9) ok
//        Minor.Major6.Major9.BassOn3() // m(6 9)/3ª ok
//        Minor.Minor7.BassOn5() // m7/5ª ok
//        Minor.Minor7.Major9() // m7(9) ok
//        Minor.Minor7.Major9.BassOn5() // m7(9)/5ª ok
//        Minor.BassOn7() // m/7ª ok
//        Minor.Minor7.Dim5() // m7(b5)
//        Minor.Minor7.Per11() // m7(11)
//        Minor.Minor7.Major9.Per11() // m7(9 11)
//        Minor.Major7() // m(7+)
//        Minor.Major6.Major7() // m(6 7+) ok
//        Minor.Major7.Major9() // m(7+ 9)
    }


    object Minor {

        operator fun invoke(tonic: Int, note: String) {
            val name = note + "m"
            val a = chord(
                // Like Am
                name,
                i(1..5, tonic),
                r(4, tonic + 2),
                p(3, tonic + 2),
                m(2, tonic + 1),
            )
            val b = chord(
                // Like Em
                name,
                i(1..6, tonic),
                r(5, tonic + 2),
                p(4, tonic + 2),
            )
            val c = chord(
                // Like Dm
                name,
                i(4, tonic),
                r(3, tonic + 2),
                p(2, tonic + 3),
                m(1, tonic + 1),
            )
            val d = chord(
                name,
                p(5, tonic),
                m(4, tonic - 2),
                i(3, tonic - 3),
                r(2, tonic - 2)
            )
            val e = chord(
                name,
                m(6, tonic),
                i(4, tonic - 3),
                r(3, tonic),
                p(2, tonic)
            )
            val f = chord(
                name,
                m(5, tonic),
                i(3, tonic - 3),
                p(2, tonic + 1),
                r(1, tonic)
            )
        }

        object Minor7 {

            operator fun invoke() {

            }

            object BassOn5 {
                operator fun invoke() {}
            }

            object Major9 {
                operator fun invoke() {}

                object BassOn5 {
                    operator fun invoke() {}
                }

                object Per11 {
                    operator fun invoke() {}
                }
            }

            object Dim5 {
                operator fun invoke() {}
            }

            object Per11 {
                operator fun invoke() {}
            }
        }

        object BassOn3 {
            operator fun invoke(tonic: Int, note: String) {
//                val name =

            }
        }

        object BassOn5 {
            operator fun invoke() {}
        }

        object BassOn7 {
            operator fun invoke() {}
        }

        object Add9 {
            operator fun invoke() {}
        }

        object Major6 {

            operator fun invoke() {}

            object BassOn3 {
                operator fun invoke() {}
            }

            object BassOn5 {
                operator fun invoke() {}
            }

            object Major9 {

                operator fun invoke() {}

                object BassOn3 {
                    operator fun invoke() {}
                }
            }

            object Major7 {
                operator fun invoke() {}
            }
        }

        object Major7 {
            operator fun invoke() {}

            object Major9 {
                operator fun invoke() {}
            }
        }
    }

    object Dim {

        object Flat13 {
            operator fun invoke() {}

        }

        object Major7 {
            operator fun invoke() {}

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