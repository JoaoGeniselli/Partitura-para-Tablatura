package com.dosei.music.scoreconverter.ui.view

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposableTablature(
    modifier: Modifier = Modifier,
    strings: Int = 6,
    positions: Map<Int, Int> = mapOf()
) {
    val paint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        textSize = 30f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    val paint2 = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        textSize = 30f
        textAlign = android.graphics.Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(20.dp * strings)) {
        val positionSize = 16.dp.toPx()
        val itemPadding = 2.dp.toPx()
        val lineSize = positionSize + itemPadding * 2f
        val stroke = 1.dp.toPx()
        var yCursor = 0f

        val startX = 16.dp.toPx()
        val endX = size.width - 16.dp.toPx()

        val ids = listOf("e", "B", "G", "D", "A", "E")

        for (string in 1..strings) {
            val rectY = yCursor + itemPadding
            val lineY = rectY + (positionSize / 2)
            val id = ids[string.dec()]

            drawLine(
                color = Color.Black,
                strokeWidth = stroke,
                start = Offset(x = startX, y = lineY),
                end = Offset(x = endX, y = lineY)
            )

            drawRoundRect(
                color = Color.White,
                cornerRadius = CornerRadius(x = 4f, y = 4f),
                topLeft = Offset(x = (size.width / 2f) - 16.dp.toPx(), y = rectY),
                size = Size(width = 32.dp.toPx(), height = positionSize)
            )

            drawRoundRect(
                color = Color.Black,
                cornerRadius = CornerRadius(x = 4f, y = 4f),
                topLeft = Offset(x = (size.width / 2f) - 16.dp.toPx(), y = rectY),
                size = Size(width = 32.dp.toPx(), height = positionSize),
                style = Stroke(width = 1.dp.toPx())
            )

            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    id,
                    0f,
                    lineY + 3.dp.toPx(),
                    paint
                )

                val text = positions[string]?.toString() ?: "-"
                it.nativeCanvas.drawText(
                    text,
                    size.width / 2f,
                    lineY + 4.dp.toPx(),
                    paint2
                )
            }
            yCursor += lineSize
        }

        drawLine(
            color = Color.Black,
            strokeWidth = stroke,
            start = Offset(x = startX, y = (positionSize / 2f) + itemPadding),
            end = Offset(x = startX, y = yCursor - itemPadding - (positionSize / 2f))
        )

        drawLine(
            color = Color.Black,
            strokeWidth = stroke,
            start = Offset(x = endX, y = (positionSize / 2f) + itemPadding),
            end = Offset(x = endX, y = yCursor - itemPadding - (positionSize / 2f))
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableTablature() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableTablature(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            strings = 6,
            positions = mapOf(
                1 to 0,
                2 to 5,
                3 to 10
            )
        )
    }
}