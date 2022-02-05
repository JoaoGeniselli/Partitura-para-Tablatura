package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.ui.R
import com.dosei.music.scoreconverter.ui.view.NotationNotes.*
import kotlin.math.roundToInt

@Composable
fun ComposableScore(
    modifier: Modifier = Modifier,
    noteIndex: Int = G3.index,
    onUpdateNoteIndex: (Int) -> Unit
) {
    val noteRange = B6.index..E2.index
    val notes = NotationNotes.getAll(noteRange)

    val vector = ImageVector.vectorResource(id = R.drawable.ic_treble_clef)
    val painter = rememberVectorPainter(image = vector)
    val height = 16.dp * (notes.count { it.isLine.not() } - 1)
    val heightInPx = LocalDensity.current.run { height.toPx() }
    val noteSizeInPx = LocalDensity.current.run { 8.dp.toPx() }

    val offsetY = remember { mutableStateOf(noteIndex * heightInPx) }

    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(height)
        .draggable(
            orientation = Orientation.Vertical,
            state = rememberDraggableState { delta ->
                val updated = (offsetY.value + delta)
                val valueInRange = maxOf(0f, minOf(updated, heightInPx))
                offsetY.value = valueInRange
                val newNoteIndex = (valueInRange / noteSizeInPx).roundToInt()
                onUpdateNoteIndex(newNoteIndex)
            }
        )
    ) {
        val noteSize = 16.dp.toPx()
        val stroke = 1.dp.toPx()
        var yCursor = 0f

        drawClef(noteSize, painter)

        notes.sortedBy { note -> note.index }.forEach { note ->
            val yPosition = yCursor + (noteSize / 2f)
            if (note.isLine) {
                drawHorizontalLine(
                    color = if (note.isMainLine) Color.Black else Color.LightGray,
                    stroke = stroke,
                    startX = 0f,
                    endX = size.width,
                    y = yPosition
                )
            }
            yCursor = yPosition
        }

        drawVerticalLine(
            stroke = stroke,
            startY = 9 * noteSize,
            endY = 13 * noteSize,
            x = 0f
        )

        drawVerticalLine(
            stroke = stroke,
            startY = 9 * noteSize,
            endY = 13 * noteSize,
            x = size.width
        )

        drawNote(noteIndex, noteSizeInPx, noteSize)
    }
}

private fun DrawScope.drawClef(
    noteSize: Float,
    painter: VectorPainter
) = inset(vertical = noteSize * 8f, horizontal = 8.dp.toPx()) {
    with(painter) {
        draw(painter.intrinsicSize)
    }
}

private fun DrawScope.drawHorizontalLine(
    color: Color,
    stroke: Float,
    startX: Float,
    endX: Float,
    y: Float
) = drawLine(
    color = color,
    strokeWidth = stroke,
    start = Offset(x = startX, y = y),
    end = Offset(x = endX, y = y)
)


private fun DrawScope.drawVerticalLine(
    stroke: Float,
    startY: Float,
    endY: Float,
    x: Float
) = drawLine(
    color = Color.Black,
    strokeWidth = stroke,
    start = Offset(x = x, y = startY),
    end = Offset(x = x, y = endY)
)

private fun DrawScope.drawNote(
    noteIndex: Int,
    noteSizeInPx: Float,
    noteSize: Float
) = drawOval(
    color = Color.Black,
    topLeft = Offset(x = size.width / 2f, y = noteIndex.toFloat() * noteSizeInPx),
    size = Size(width = noteSize + 4.dp.toPx(), height = noteSize)
)

@Preview(showBackground = true)
@Composable
private fun PreviewComposableScore() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableScore(
            modifier = Modifier
                .padding(16.dp),
            noteIndex = E4.index,
            onUpdateNoteIndex = {}
        )
    }
}