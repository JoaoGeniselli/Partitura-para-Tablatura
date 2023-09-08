package com.dosei.music.scoreconverter.ui.view

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.scoreconverter.ui.theme.AppTheme

@Composable
fun Tablature(
    modifier: Modifier = Modifier,
    strings: Int = 6,
    positions: Map<Int, Int?> = mapOf()
) {
    val lineColor = MaterialTheme.colorScheme.onSurface
    val spaceColor = MaterialTheme.colorScheme.surface
    val textSizeDp = LocalDensity.current.run { 18.sp.toPx() }
    val paint = Paint().asFrameworkPaint().apply {
        color = lineColor.hashCode()
        isAntiAlias = true
        textSize = textSizeDp
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    val paint2 = Paint().asFrameworkPaint().apply {
        color = lineColor.hashCode()
        isAntiAlias = true
        textSize = textSizeDp
        textAlign = android.graphics.Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp * strings)
    ) {
        val positionSize = textSizeDp + 8.dp.toPx()
        val itemPadding = 2.dp.toPx()
        val lineSize = positionSize + itemPadding * 2f
        val stroke = 1.dp.toPx()
        var yCursor = 0f

        val startX = 20.dp.toPx()
        val endX = size.width - 20.dp.toPx()

        val ids = listOf("E", "B", "G", "D", "A", "E")

        for (string in 1..strings) {
            val rectY = yCursor + itemPadding
            val lineY = rectY + (positionSize / 2)
            val id = ids[string.dec()]

            drawLine(
                color = lineColor,
                strokeWidth = stroke,
                start = Offset(x = startX, y = lineY),
                end = Offset(x = endX, y = lineY)
            )

            drawRoundRect(
                color = spaceColor,
                cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx()),
                topLeft = Offset(x = (size.width / 2f) - 24.dp.toPx(), y = rectY),
                size = Size(width = 48.dp.toPx(), height = positionSize)
            )

            drawRoundRect(
                color = lineColor,
                cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx()),
                topLeft = Offset(x = (size.width / 2f) - 24.dp.toPx(), y = rectY),
                size = Size(width = 48.dp.toPx(), height = positionSize),
                style = Stroke(width = 1.dp.toPx())
            )

            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    id,
                    0f,
                    lineY + 7.dp.toPx(),
                    paint
                )

                val text = positions[string]?.toString() ?: "-"
                it.nativeCanvas.drawText(
                    text,
                    size.width / 2f,
                    lineY + 7.dp.toPx(),
                    paint2
                )
            }
            yCursor += lineSize
        }

        drawLine(
            color = lineColor,
            strokeWidth = stroke,
            start = Offset(x = startX, y = (positionSize / 2f) + itemPadding),
            end = Offset(x = startX, y = yCursor - itemPadding - (positionSize / 2f))
        )

        drawLine(
            color = lineColor,
            strokeWidth = stroke,
            start = Offset(x = endX, y = (positionSize / 2f) + itemPadding),
            end = Offset(x = endX, y = yCursor - itemPadding - (positionSize / 2f))
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableTablature() {
    AppTheme(darkTheme = true) {
        Surface {
            Tablature(
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
}