package com.dosei.music.scoreconverter.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.ui.view.*

@Composable
fun ComposableConverter(modifier: Modifier = Modifier) {
    val guitar = Guitar.default()
    val noteIndex = remember { mutableStateOf(NotationNotes.E2.index) }
    val selectedDecorationIndex = remember { mutableStateOf(1) }
    val decorator = remember { mutableStateOf(ScoreNoteDecoration.NATURAL) }
    Column(modifier.padding(16.dp)) {
        ComposableScore(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
                .testTag("score"),
            noteIndex = noteIndex.value,
            onUpdateNoteIndex = { noteIndex.value = it },
            noteDecoration = decorator.value
        )
        val note = noteIndex.value.toNote(decorator.value)
        ComposableTablature(
            modifier = Modifier.padding(8.dp),
            positions = guitar.tuning.run {
                if (note != null) {
                    mapOf(
                        1 to string1.positionOf(note),
                        2 to string2.positionOf(note),
                        3 to string3.positionOf(note),
                        4 to string4.positionOf(note),
                        5 to string5.positionOf(note),
                        6 to string6.positionOf(note),
                    )
                } else {
                    mapOf()
                }
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = "Nota: ${note?.toString()}"
            )

            ToggleIconRow(
                modifier = Modifier.padding(start = 8.dp),
                selectedIndex = selectedDecorationIndex.value,
                onSelectIndex = {
                    selectedDecorationIndex.value = it
                    when (it) {
                        0 -> decorator.value = ScoreNoteDecoration.FLAT
                        1 -> decorator.value = ScoreNoteDecoration.NATURAL
                        2 -> decorator.value = ScoreNoteDecoration.SHARP
                    }
                },
                iconSize = 24.dp,
                icons = listOf(
                    Icon(
                        painter = painterResource(id = R.drawable.ic_flat_black),
                        description = "flat button"
                    ),
                    Icon(
                        painter = painterResource(id = R.drawable.ic_natural_note),
                        description = "natural button"
                    ),
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sharp_black),
                        description = "sharp button"
                    )
                )
            )
        }
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