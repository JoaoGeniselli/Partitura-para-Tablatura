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
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

        fun totalNotes() = values().size
    }
}

@Composable
fun ComposableScore(modifier: Modifier = Modifier, noteRange: IntRange, noteIndex: Int? = null) {
    val notes = NotationNotes.getAll(noteRange)

    Canvas(modifier = modifier) {
        val noteSize = 16.dp.toPx()
        val stroke = 1.dp.toPx()
        var yCursor = 0f

        notes.sortedByDescending { it.index }.forEach {
            val yPosition = yCursor + (noteSize / 2f)
            if (it.isLine) {
                drawLine(
                    color = if (it.isMainLine) Color.Black else Color.LightGray,
                    strokeWidth = stroke,
                    start = Offset(x = 0f, y = yPosition),
                    end = Offset(x = size.width, y = yPosition)
                )
            }

            if (noteIndex == it.index) {
                drawOval(
                    color = Color.Black,
                    topLeft = Offset(x = size.width / 2f, y = yCursor),
                    size = Size(width = noteSize + 4.dp.toPx(), height = noteSize)
                )
            }
            yCursor = yPosition

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableScore() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableScore(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            noteRange = E2.index..B6.index,
            noteIndex = E4.index
        )
    }
}