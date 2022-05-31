package com.dosei.music.scoreconverter.toolbox

import Transpose
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import java.util.regex.Pattern

class BeautifySong(
    regex: String = Transpose.fileChordsQuery
) {
    private val pattern = Pattern.compile(regex)

    operator fun invoke(song: AnnotatedString): AnnotatedString {
        val matcher = pattern.matcher(song)
        return buildAnnotatedString {
            append(song.toString())

            while (matcher.find()) {
                addStyle(SpanStyle(fontWeight = FontWeight.Bold), matcher.start(), matcher.end())
            }
        }
    }
}