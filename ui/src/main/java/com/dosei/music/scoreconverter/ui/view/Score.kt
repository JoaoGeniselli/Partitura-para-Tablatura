package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.ui.R
import com.dosei.music.scoreconverter.ui.theme.AppTheme
import com.dosei.music.scoreconverter.ui.view.NotationNotes.*
import com.dosei.music.scoreconverter.ui.view.ScoreNoteDecoration.*
import kotlin.math.roundToInt

@Composable
fun Score(
    modifier: Modifier = Modifier,
    noteIndex: Int = G3.index,
    noteDecoration: ScoreNoteDecoration = NATURAL,
    onUpdateNoteIndex: (Int) -> Unit
) {
    val noteRange = D6.index..E2.index
    val notes = NotationNotes.getAll(noteRange)

    val decoratorPainter = when(noteDecoration) {
        FLAT -> rememberVector(id = R.drawable.ic_flat_black)
        SHARP -> rememberVector(id = R.drawable.ic_sharp_black)
        else -> null
    }
    val clefPainter = rememberVector(id = R.drawable.ic_treble_clef)

    val height = 16.dp * (notes.count { it.isLine.not() } - 0.5f)
    val heightInPx = LocalDensity.current.run { height.toPx() }
    val noteSizeInPx = LocalDensity.current.run { 8.dp.toPx() }

    val offsetY = remember { mutableStateOf(noteIndex * heightInPx) }

    val lineColor = MaterialTheme.colors.onSurface
    val supplementaryLineColor = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)

    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(height + 24.dp)
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
        val stroke = 2.dp.toPx()
        var yCursor = 0f

        notes.sortedBy { note -> note.index }.forEach { note ->
            val yPosition = yCursor + (noteSize / 2f)
            if (note.isLine) {
                drawHorizontalLine(
                    color = if (note.isMainLine) lineColor else supplementaryLineColor,
                    stroke = stroke,
                    startX = 0f,
                    endX = size.width,
                    y = yPosition
                )
            }
            yCursor = yPosition
        }

        drawVerticalLine(
            color = lineColor,
            stroke = stroke,
            startY = 6.5f * noteSize,
            endY = 10.5f * noteSize,
            x = 0f
        )

        drawVerticalLine(
            color = lineColor,
            stroke = stroke,
            startY = 6.5f * noteSize,
            endY = 10.5f * noteSize,
            x = size.width
        )

        drawClef(
            color = lineColor,
            noteSize = noteSize,
            painter = clefPainter
        )

        drawNote(
            color = lineColor,
            noteIndex = noteIndex,
            stroke = stroke,
            noteSizeInPx = noteSizeInPx,
            noteSize = noteSize,
            noteDecoration = noteDecoration,
            decorationPainter = decoratorPainter
        )
    }
}

@Composable
private fun rememberVector(id: Int) =
    rememberVectorPainter(image = ImageVector.vectorResource(id = id))

private fun DrawScope.drawClef(
    color: Color,
    noteSize: Float,
    painter: VectorPainter
) = inset(vertical = noteSize * 5.4f, horizontal = 8.dp.toPx()) {
    with(painter) {
        draw(
            colorFilter = ColorFilter.tint(color),
            size = painter.intrinsicSize
        )
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
    color: Color,
    stroke: Float,
    startY: Float,
    endY: Float,
    x: Float
) = drawLine(
    color = color,
    strokeWidth = stroke,
    start = Offset(x = x, y = startY),
    end = Offset(x = x, y = endY)
)

private fun DrawScope.drawNote(
    color: Color,
    noteIndex: Int,
    stroke: Float,
    noteSizeInPx: Float,
    noteSize: Float,
    noteDecoration: ScoreNoteDecoration?,
    decorationPainter: VectorPainter?
) {
    val note = NotationNotes.getByIndex(noteIndex) ?: return
    val width = noteSize + 4.dp.toPx()
    val startX = size.width / 2f
    val endX = size.width / 2f + width
    val noteY = noteIndex.toFloat() * noteSizeInPx

    rotate(degrees = -25f, Offset(x = startX + width / 2f, y = noteY + noteSize / 2f)) {
        drawOval(
            color = color,
            topLeft = Offset(x = startX, y = noteY),
            size = Size(width = width, height = noteSize)
        )
    }
    drawVerticalLine(
        color = color,
        stroke = stroke,
        startY = noteY + noteSizeInPx,
        endY = (note.tailIndex.inc() * noteSizeInPx),
        x = if (note.tailInStart) startX + stroke / 2f else endX - stroke / 2f
    )
    note.supplementaryLines.forEach {
        drawHorizontalLine(
            color = color,
            stroke = stroke,
            startX = startX - 8.dp.toPx(),
            endX = endX + 8.dp.toPx(),
            y = it.inc() * noteSizeInPx
        )
    }
    decorationPainter?.run {
        val decoratorSize = intrinsicSize.scale(0.8f)
        val decoratorX = startX - decoratorSize.width - 10.dp.toPx()
        val decoratorY = noteY - decoratorSize.height * (noteDecoration?.topPaddingDiff ?: 1f)
        inset(left = decoratorX, top = decoratorY, right = 0f, bottom = 0f) {
            draw(
                colorFilter = ColorFilter.tint(color),
                size = decoratorSize
            )
        }
    }
}

private fun Size.scale(multiplier: Float) =
    Size(this.width * multiplier, this.height * multiplier)

@Preview(showBackground = true)
@Composable
private fun PreviewComposableScore() {
    AppTheme(darkTheme = true) {
        Surface {
            Score(
                modifier = Modifier
                    .padding(16.dp),
                noteIndex = C4.index,
                onUpdateNoteIndex = {},
                noteDecoration = SHARP
            )
        }
    }
}