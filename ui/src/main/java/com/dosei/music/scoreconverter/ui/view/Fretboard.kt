package com.dosei.music.scoreconverter.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.arpeggio.GuitarStringAndFret
import com.dosei.music.scoreconverter.ui.theme.AppTheme

@OptIn(ExperimentalTextApi::class)
@Composable
fun GuitarNeck(
    modifier: Modifier = Modifier,
    highlights: GuitarStringAndFret = emptyMap(),
    fretboardColor: Color = MaterialTheme.colorScheme.onSurface,
    highlightColor: Color = MaterialTheme.colorScheme.error,
    fretMarkStyle: TextStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSurface)
) {
    val measurer = rememberTextMeasurer()

    Canvas(modifier = modifier.fillMaxHeight()) {
        val padding = 16.dp.toPx()
        val style = Stroke(4.dp.toPx())

        val fretboardLeading = padding / 2f + 24.dp.toPx()
        val fretboardTrailing = size.width - padding / 2f

        val nutHeight = 8.dp.toPx()
        val nutBottom = nutHeight
        val nutPath = Path().apply {
            moveTo(fretboardLeading, nutBottom)
            lineTo(fretboardLeading, 0f)
            lineTo(fretboardTrailing, 0f)
            lineTo(fretboardTrailing, nutBottom)
        }
        drawPath(path = nutPath, color = fretboardColor, style = style)

        val fretboardBottom = size.height
        val fretboardWidth = fretboardTrailing - fretboardLeading
        val fretboardHeight = fretboardBottom - nutBottom

        val stringXs = (0..5).map { (fretboardWidth / 5) * it + fretboardLeading }

        var refFretY = fretboardHeight * 0.07f
        var lastFretY = nutBottom

        val fretYs = (1..19).map { fret ->
            (refFretY + lastFretY).also { calc ->
                refFretY -= refFretY * 0.037f
                lastFretY = calc
            }
        }

        val markedFrets = listOf(3, 5, 7, 9, 12, 15, 17, 19)
        val fretMarks = markedFrets.map { fret ->
            val index = fret.dec()
            fret to (fretYs[index] - fretYs[index.dec()]) / 2 + fretYs[index.dec()]
        }
        val fretboardPath = Path().apply {
            moveTo(fretboardLeading, nutBottom)
            lineTo(fretboardTrailing, nutBottom)
            moveTo(fretboardTrailing, fretboardBottom)
            lineTo(fretboardLeading, fretboardBottom)

            fretYs.forEach { fretY ->
                moveTo(fretboardLeading, fretY)
                lineTo(fretboardTrailing, fretY)
            }

            stringXs.forEach { stringX ->
                moveTo(stringX, nutBottom)
                lineTo(stringX, fretboardBottom)
            }

            fretMarks.forEach { (fret, fretY) ->
                moveTo(fretboardLeading, fretY)
                addOval(
                    oval = Rect(
                        center = Offset(fretboardWidth / 2f + fretboardLeading, fretY),
                        2.dp.toPx()
                    )
                )
                drawText(
                    textMeasurer = measurer,
                    text = fret.toString(),
                    topLeft = Offset(0f, fretY - 10.sp.toPx()),
                    style = fretMarkStyle
                )
            }
        }
        drawPath(path = fretboardPath, color = fretboardColor, style = style)

        val highlightsPath = Path().apply {
            highlights.forEach { (guitarString, fret) ->
                val t = stringXs.size
                val hX = stringXs[t - guitarString]

                val t2 = fretYs.size
                val fretIndex = t2 - (t2 - (fret?.dec() ?: 0))
                val hY = (fretYs[fretIndex] - fretYs[fretIndex.dec()]) / 2 + fretYs[fretIndex.dec()]

                addOval(
                    oval = Rect(
                        center = Offset(hX, hY),
                        9.dp.toPx()
                    ),
                )
            }
        }
        drawPath(highlightsPath, color = highlightColor, style = Fill)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewGuitarNeck() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(contentAlignment = Alignment.Center) {
                GuitarNeck(
                    modifier = Modifier
                        .padding(16.dp)
                        .width(156.dp),
                    highlights = mapOf(
                        1 to 3,
                        2 to 8,
                        3 to 12,
                    )
                )
            }
        }
    }
}