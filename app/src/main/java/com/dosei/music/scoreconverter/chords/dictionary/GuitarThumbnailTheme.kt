package com.dosei.music.scoreconverter.chords.dictionary

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.arpeggio.theme.ArpeggioTheme
import com.dosei.music.arpeggio.theme.Sizes
import com.dosei.music.arpeggio.theme.Typography

@Composable
fun GuitarThumbnailTheme(
    content: @Composable () -> Unit
) {
    ArpeggioTheme(
        typography = Typography(
            name = TextStyle().copy(fontSize = 14.sp),
            firstFretIndicator = TextStyle(fontSize = 14.sp),
            fingerIndicator = TextStyle(fontSize = 10.sp, color = MaterialTheme.colors.onPrimary)
        ),
        sizes = Sizes(position = 16.dp),
        content = content
    )
}