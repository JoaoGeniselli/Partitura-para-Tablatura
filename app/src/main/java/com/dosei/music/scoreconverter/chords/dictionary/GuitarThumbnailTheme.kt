package com.dosei.music.scoreconverter.chords.dictionary

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.arpeggio.theme.ArpeggioTheme
import com.dosei.music.arpeggio.theme.FormatInitialFret
import com.dosei.music.arpeggio.theme.Sizes
import com.dosei.music.arpeggio.theme.Typography

@Composable
fun GuitarThumbnailTheme(
    content: @Composable () -> Unit
) {
    ArpeggioTheme(
        typography = Typography(
            name = TextStyle().copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            firstFretIndicator = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
            fingerIndicator = TextStyle(fontSize = 10.sp, color = MaterialTheme.colorScheme.onPrimary)
        ),
        sizes = Sizes(position = 16.dp),
        content = content,
        formatInitialFret = FormatInitialFret.ptBr()
    )
}