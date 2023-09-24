package com.dosei.music.scoreconverter.feature.chords.dictionary.data

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.Barre
import com.dosei.music.arpeggio.Finger
import com.dosei.music.arpeggio.OpenString
import com.dosei.music.arpeggio.Position

class ChordParser {

    fun parse(pattern: String): Chord {
        val split = pattern.split(GROUP_DELIMITER)
        val name = split.getOrNull(SLOT_NAME).orEmpty()

        val positions = split.subList(SLOT_FIRST_POSITION, split.size)
            .mapNotNull { parseGroup(it) }

        val positionsWithBarres = positions
            .groupBy { (it as? Position)?.finger }
            .map { (finger, positions) ->
                when {
                    finger == null -> positions
                    positions.size == 1 -> positions
                    else -> {
                        val minString = positions.minOf { it.string }
                        val maxString = positions.maxOf { it.string }
                        val barre = Barre(
                            fret = positions.first().fret,
                            strings = minString..maxString,
                            finger = finger
                        )
                        listOf(barre)
                    }
                }
            }
            .flatten()
            .map { if (it is Position && it.fret == 0) OpenString(it.string) else it }

        return Chord(AnnotatedString(name), positionsWithBarres)
    }

    private fun parseGroup(group: String): Position? {
        return when {
            REGEX_POSITION_WITH_FINGER.matches(group) -> {
                val values = group.split(VALUE_DELIMITER)
                Position(
                    fret = values[SLOT_POSITION_FRET].toInt(),
                    string = values[SLOT_POSITION_STRING].toInt(),
                    finger = values[SLOT_POSITION_FINGER].toFinger()
                )
            }
            REGEX_POSITION.matches(group) -> {
                val values = group.split(VALUE_DELIMITER)
                Position(
                    fret = values[SLOT_POSITION_FRET].toInt(),
                    string = values[SLOT_POSITION_STRING].toInt(),
                    finger = null
                )
            }
            else -> null
        }
    }

    private fun String.toFinger() = when (this) {
        FINGER_INDEX -> Finger.Index
        FINGER_MIDDLE -> Finger.Middle
        FINGER_RING -> Finger.Ring
        FINGER_PINKY -> Finger.Pinky
        else -> null
    }

    companion object {
        const val GROUP_DELIMITER: String = ","
        const val VALUE_DELIMITER: String = "."

        private const val SLOT_NAME = 0
        private const val SLOT_FIRST_POSITION = 1

        private const val FINGER_INDEX = "i"
        private const val FINGER_MIDDLE = "m"
        private const val FINGER_RING = "r"
        private const val FINGER_PINKY = "p"

        private const val SLOT_POSITION_STRING = 0
        private const val SLOT_POSITION_FRET = 1
        private const val SLOT_POSITION_FINGER = 2

        private val REGEX_POSITION_WITH_FINGER = "\\d+\\.\\d+\\.[imrp]".toRegex()
        private val REGEX_POSITION = "\\d+\\.\\d+".toRegex()
    }
}