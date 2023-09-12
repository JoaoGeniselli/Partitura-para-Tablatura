package com.dosei.music.scoreconverter.feature.chords.dictionary

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.arpeggio.ChordDiagram
import com.dosei.music.arpeggio.Component
import com.dosei.music.arpeggio.theme.ArpeggioTheme
import com.dosei.music.arpeggio.theme.Colors
import com.dosei.music.arpeggio.theme.FormatInitialFret
import com.dosei.music.arpeggio.theme.Sizes
import com.dosei.music.arpeggio.theme.Typography
import com.dosei.music.scoreconverter.feature.chords.dictionary.data.Chords
import com.dosei.music.scoreconverter.ui.theme.AppTheme

@Composable
fun GuitarThumbnailTheme(
    content: @Composable () -> Unit
) {
    ArpeggioTheme(
        typography = Typography(
            name = TextStyle().copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            firstFretIndicator = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
            fingerIndicator = TextStyle(
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        ),
        sizes = Sizes(position = 16.dp),
        content = content,
        formatInitialFret = FormatInitialFret.ptBr(),
        colors = Colors(
            grid = MaterialTheme.colorScheme.onSurface,
            position = MaterialTheme.colorScheme.onSurface,
            stringUsageIndicator = MaterialTheme.colorScheme.onSurface,
        )
    )
}