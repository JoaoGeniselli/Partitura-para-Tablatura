package com.dosei.music.scoreconverter.feature.chords.dictionary.data

import androidx.compose.ui.text.AnnotatedString
import com.dosei.music.arpeggio.Barre
import com.dosei.music.arpeggio.Component
import com.dosei.music.arpeggio.Finger
import com.dosei.music.arpeggio.OpenString
import com.dosei.music.arpeggio.Position

class ChordParser {

    fun parse(pattern: String): Chord {
        val split = pattern.split(GROUP_DELIMITER)
        val name = split.getOrNull(SLOT_NAME).orEmpty()

        val fingerSettings = listOfNotNull(
            split.getOrNull(SLOT_FINGER_INDEX)?.let { parseGroup(it, Finger.Index) },
            split.getOrNull(SLOT_FINGER_MIDDLE)?.let { parseGroup(it, Finger.Middle) },
            split.getOrNull(SLOT_FINGER_RING)?.let { parseGroup(it, Finger.Ring) },
            split.getOrNull(SLOT_FINGER_PINKY)?.let { parseGroup(it, Finger.Pinky) },
        )

        val openStrings = (SLOT_OPEN_STRINGS..split.lastIndex).mapNotNull {
            parseGroup(split[it], null)
        }

        return Chord(AnnotatedString(name), fingerSettings + openStrings)
    }

    private fun parseGroup(group: String, finger: Finger?): Component? {
        return when {
            REGEX_BARRE.matches(group) -> {
                val values = group.split(VALUE_DELIMITER).map { it.toInt() }
                Barre(
                    strings = values[SLOT_BARRE_START]..values[SLOT_BARRE_END],
                    fret = values[SLOT_BARRE_FRET],
                    finger = finger
                )
            }

            REGEX_POSITION.matches(group) -> {
                val values = group.split("-").map { it.toInt() }
                Position(
                    string = values[SLOT_POSITION_STRING],
                    fret = values[SLOT_POSITION_FRET],
                    finger = finger
                )
            }

            REGEX_OPEN_STRING.matches(group) -> {
                OpenString(
                    string = group.toInt(),
                )
            }

            else -> null
        }
    }

    companion object {
        const val GROUP_DELIMITER: String = ","
        const val VALUE_DELIMITER: String = "-"

        private const val SLOT_NAME = 0
        private const val SLOT_FINGER_INDEX = 1
        private const val SLOT_FINGER_MIDDLE = 2
        private const val SLOT_FINGER_RING = 3
        private const val SLOT_FINGER_PINKY = 4
        private const val SLOT_OPEN_STRINGS = 5

        private const val SLOT_BARRE_START = 0
        private const val SLOT_BARRE_END = 1
        private const val SLOT_BARRE_FRET = 2

        private const val SLOT_POSITION_STRING = 0
        private const val SLOT_POSITION_FRET = 1

        private val REGEX_BARRE = "\\d-\\d-\\d".toRegex()
        private val REGEX_POSITION = "\\d-\\d".toRegex()
        private val REGEX_OPEN_STRING = "\\d".toRegex()
    }
}