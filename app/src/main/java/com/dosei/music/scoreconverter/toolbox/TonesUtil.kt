package com.dosei.music.scoreconverter.toolbox

import androidx.compose.runtime.Composable
import java.text.DecimalFormat
import java.util.Locale

private const val SEMITONES_IN_TONE = 2

@Composable
fun Int.formatSemitonesToTones(): String {
    val semitones = this.toDouble()
    val tones = semitones / SEMITONES_IN_TONE
    return DecimalFormat.getInstance(Locale.getDefault())
        .run {
            maximumFractionDigits = 1
            format(tones)
        }
        .let { if (tones > 0.0) "+$it" else it }
        .orEmpty()
}

@Composable
fun Int.formatTonesShort(): String {
    val semitones = this.toDouble()
    val tones = semitones / SEMITONES_IN_TONE
    return DecimalFormat.getInstance(Locale.getDefault())
        .run {
            maximumFractionDigits = 1
            format(tones)
        }
        .let { if (tones > 0.0) "+${it}T" else "${it}T" }
}