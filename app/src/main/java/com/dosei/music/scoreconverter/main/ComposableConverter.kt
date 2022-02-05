package com.dosei.music.scoreconverter.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.ui.view.ComposableScore
import com.dosei.music.scoreconverter.ui.view.ComposableTablature
import com.dosei.music.scoreconverter.ui.view.NotationNotes

@Composable
fun ComposableConverter(modifier: Modifier = Modifier) {
    val noteIndex = remember { mutableStateOf(NotationNotes.G3.index) }
    Column(modifier.padding(16.dp)) {
        ComposableScore(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
                .testTag("score"),
            noteIndex = noteIndex.value,
            onUpdateNoteIndex = { noteIndex.value = it }
        )
        Text(text = noteIndex.value.toString())
        ComposableTablature(Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableConverter() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableConverter(
            modifier = Modifier
        )
    }
}