package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposableTablature(modifier: Modifier = Modifier, strings: Int = 6, positions: Map<Int, Int>) {
    Canvas(modifier = modifier) {
        val positionSize = 16.dp.toPx()
        val stroke = 1.dp.toPx()
        var yCursor = 0f
        val itemPadding = 2.dp.toPx()

        val startX = 16.dp.toPx()
        val endX = size.width - 16.dp.toPx()


        for (string in 1..strings) {
            val yPosition = yCursor + positionSize + itemPadding

            drawLine(
                color = Color.Black,
                strokeWidth = stroke,
                start = Offset(x = startX, y = yPosition),
                end = Offset(x = endX, y = yPosition)
            )
            yCursor = yPosition
        }

        drawLine(
            color = Color.Black,
            strokeWidth = stroke,
            start = Offset(x = startX, y = positionSize + itemPadding),
            end = Offset(x = startX, y = yCursor)
        )

        drawLine(
            color = Color.Black,
            strokeWidth = stroke,
            start = Offset(x = endX, y = positionSize + itemPadding),
            end = Offset(x = endX, y = yCursor)
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