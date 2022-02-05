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
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.ui.R
import com.dosei.music.scoreconverter.ui.view.NotationNotes.*
import kotlin.math.roundToInt

enum class NotationNotes(val index: Int, val isMainLine: Boolean, val isLine: Boolean) {
    B6(0, false, false),
    A6(1, false, true),
    G6(2, false, false),
    F6(3, false, true),
    E6(4, false, false),
    D6(5, false, true),
    C6(6, false, false),
    B5(7, false, true),
    A5(8, false, false),
    G5(9, false, true),
    F5(10, false, false),
    E5(11, false, true),
    D5(12, false, false),
    C5(13, false, true),
    B4(14, false, false),
    A4(15, false, true),
    G4(16, false, false),
    F4(17, true, true),
    E4(18, false, false),
    D4(19, true, true),
    C4(20, false, false),
    B3(21, true, true),
    A3(22, false, false),
    G3(23, true, true),
    F3(24, false, false),
    E3(25, true, true),
    D3(26, false, false),
    C3(27, false, true),
    B2(28, false, false),
    A2(29, false, true),
    G2(30, false, false),
    F2(31, false, true),
    E2(32, false, false);

    companion object {

        fun getAll(intRange: IntRange): List<NotationNotes> {
            return values().filter { intRange.contains(it.index) }.sortedBy { it.index }
        }
    }
}

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
    val height = 16.dp * notes.count { it.isLine.not() }
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

        inset(vertical = noteSize * 8f, horizontal = 8.dp.toPx()) {
            with(painter) {
                draw(painter.intrinsicSize)
            }
        }

        notes.sortedBy { note -> note.index }.forEach { note ->
            val yPosition = yCursor + (noteSize / 2f)
            if (note.isLine) {
                drawLine(
                    color = if (note.isMainLine) Color.Black else Color.LightGray,
                    strokeWidth = stroke,
                    start = Offset(x = 0f, y = yPosition),
                    end = Offset(x = size.width, y = yPosition)
                )
            }
            yCursor = yPosition
        }

        drawLine(
            color = Color.Black,
            strokeWidth = stroke,
            start = Offset(x = 0f, y = 9 * noteSize),
            end = Offset(x = 0f, y = 13 * noteSize)
        )

        drawLine(
            color = Color.Black,
            strokeWidth = stroke,
            start = Offset(x = size.width, y = 9 * noteSize),
            end = Offset(x = size.width, y = 13 * noteSize)
        )

        drawOval(
            color = Color.Black,
            topLeft = Offset(x = size.width / 2f, y = noteIndex.toFloat() * noteSizeInPx),
            size = Size(width = noteSize + 4.dp.toPx(), height = noteSize)
        )
    }
}

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