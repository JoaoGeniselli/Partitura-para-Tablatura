package com.dosei.music.scoreconverter.toolbox

import Transpose
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

class BeautifySong(
    regex: String = Transpose.fileChordsQuery
) {
    private val pattern = regex.toRegex()

    operator fun invoke(song: AnnotatedString): AnnotatedString {

        return buildAnnotatedString {
            append(song.toString())
            pattern
                .findAll(song)
                .forEach { result ->
                    addStyle(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        result.range.first,
                        result.range.last
                    )
                }
        }
    }
}