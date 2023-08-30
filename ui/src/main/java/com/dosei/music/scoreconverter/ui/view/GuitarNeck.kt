package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GuitarNeck(modifier: Modifier = Modifier) {
    val fretboardColor = MaterialTheme.colors.onSurface

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
            }
        }
        drawPath(path = fretboardPath, color = fretboardColor, style = style)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGuitarNeck() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Box(contentAlignment = Alignment.Center) {
            GuitarNeck(
                modifier = Modifier
                    .padding(16.dp)
                    .width(156.dp)
            )
        }
    }
}