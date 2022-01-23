package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object NotationNotes {
    const val B6 = 32
    const val A6 = 31
    const val G6 = 30
    const val F6 = 29
    const val E6 = 28
    const val D6 = 27
    const val C6 = 26
    const val B5 = 25
    const val A5 = 24
    const val G5 = 23
    const val F5 = 22
    const val E5 = 21
    const val D5 = 20
    const val C5 = 19
    const val B4 = 18
    const val A4 = 17
    const val G4 = 16
    const val F4 = 15
    const val E4 = 14
    const val D4 = 13
    const val C4 = 12
    const val B3 = 11
    const val A3 = 10
    const val G3 = 9
    const val F3 = 8
    const val E3 = 7
    const val D3 = 6
    const val C3 = 5
    const val B2 = 4
    const val A2 = 3
    const val G2 = 2
    const val F2 = 1
    const val E2 = 0
}

@Composable
fun ComposableScore(modifier: Modifier = Modifier, noteRange: IntRange) {

    Canvas(modifier = modifier) {
        val spaceSize = size.height / (noteRange.last - noteRange.first)

        inset(spaceSize) {
            noteRange.forEach {
                drawLine(
                    color = Color.Black,
                    start = Offset(x = 0f, y = it * spaceSize),
                    end = Offset(x = size.width, y = it * spaceSize)
                )
            }
        }


    }
}

class Geometry(
    val scoreStart: Offset,
    val scoreEnd: Offset,
    val scoreSize: Size
) {

    fun centerOfLine(line: Int) {

    }

    fun centerOfSpace(space: Int) {

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableScore() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableScore(
            modifier = Modifier.fillMaxSize(),
            noteRange = NotationNotes.E2..NotationNotes.B6
        )
    }
}