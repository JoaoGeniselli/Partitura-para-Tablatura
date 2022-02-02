package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.ui.R
import com.dosei.music.scoreconverter.ui.view.NotationNotes.*

enum class NotationNotes(val index: Int, val isMainLine: Boolean, val isLine: Boolean) {
    B6(32, false, false),
    A6(31, false, true),
    G6(30, false, false),
    F6(29, false, true),
    E6(28, false, false),
    D6(27, false, true),
    C6(26, false, false),
    B5(25, false, true),
    A5(24, false, false),
    G5(23, false, true),
    F5(22, false, false),
    E5(21, false, true),
    D5(20, false, false),
    C5(19, false, true),
    B4(18, false, false),
    A4(17, false, true),
    G4(16, false, false),
    F4(15, true, true),
    E4(14, false, false),
    D4(13, true, true),
    C4(12, false, false),
    B3(11, true, true),
    A3(10, false, false),
    G3(9, true, true),
    F3(8, false, false),
    E3(7, true, true),
    D3(6, false, false),
    C3(5, false, true),
    B2(4, false, false),
    A2(3, false, true),
    G2(2, false, false),
    F2(1, false, true),
    E2(0, false, false);

    companion object {

        fun getAll(intRange: IntRange): List<NotationNotes> {
            return values().filter { intRange.contains(it.index) }.sortedBy { it.index }
        }
    }
}

@Composable
fun ComposableScore(modifier: Modifier = Modifier, noteIndex: Int? = null) {
    val noteRange = E2.index..B6.index
    val notes = NotationNotes.getAll(noteRange)

    val vector = ImageVector.vectorResource(id = R.drawable.ic_treble_clef)
    val painter = rememberVectorPainter(image = vector)

    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(16.dp * notes.count { it.isLine.not() })) {
        val noteSize = 16.dp.toPx()
        val stroke = 1.dp.toPx()
        var yCursor = 0f

        inset(vertical = noteSize * 8f, horizontal = 8.dp.toPx()) {
            with(painter) {
                draw(painter.intrinsicSize)
            }
        }

        notes.sortedByDescending { note -> note.index }.forEach { note ->
            val yPosition = yCursor + (noteSize / 2f)
            if (note.isLine) {
                drawLine(
                    color = if (note.isMainLine) Color.Black else Color.LightGray,
                    strokeWidth = stroke,
                    start = Offset(x = 0f, y = yPosition),
                    end = Offset(x = size.width, y = yPosition)
                )
            }

            if (noteIndex == note.index) {
                drawOval(
                    color = Color.Black,
                    topLeft = Offset(x = size.width / 2f, y = yCursor),
                    size = Size(width = noteSize + 4.dp.toPx(), height = noteSize)
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
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableScore() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableScore(
            modifier = Modifier
                .padding(16.dp),
            noteIndex = E4.index
        )
    }
}