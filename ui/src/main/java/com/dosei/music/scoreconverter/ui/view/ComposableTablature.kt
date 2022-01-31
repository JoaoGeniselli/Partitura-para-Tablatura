package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposableTablature(modifier: Modifier = Modifier, strings: Int = 6, positions: Map<Int, Int>) {
    Canvas(modifier = modifier) {
        val positionSize = 16.dp.toPx()
        val itemPadding = 2.dp.toPx()
        val lineSize = positionSize + itemPadding * 2f
        val stroke = 1.dp.toPx()
        var yCursor = 0f

        val startX = 16.dp.toPx()
        val endX = size.width - 16.dp.toPx()


        for (string in 1..strings) {
            val rectY = yCursor + itemPadding
            val lineY = rectY + (positionSize / 2)


            drawLine(
                color = Color.Black,
                strokeWidth = stroke,
                start = Offset(x = startX, y = lineY),
                end = Offset(x = endX, y = lineY)
            )

            drawRoundRect(
                color = Color.Black,
                cornerRadius = CornerRadius(x = 4f, y = 4f),
                topLeft = Offset(x = (size.width /2f) - 16.dp.toPx(), y = rectY),
                size = Size(width = 32.dp.toPx(), height = positionSize),
                style = Stroke(width = 1.dp.toPx())
            )
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
            positions = mapOf()
        )
    }
}