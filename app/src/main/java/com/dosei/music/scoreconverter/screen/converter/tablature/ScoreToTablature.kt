package com.dosei.music.scoreconverter.screen.converter.tablature

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.arpeggio.Score
import com.dosei.music.arpeggio.ScoreNote
import com.dosei.music.arpeggio.ScoreNoteDecoration
import com.dosei.music.arpeggio.Tablature
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.main.toNote
import com.dosei.music.scoreconverter.toolbox.AdvertView
import com.dosei.music.scoreconverter.ui.theme.AppTheme
import com.dosei.music.scoreconverter.ui.view.Icon
import com.dosei.music.scoreconverter.ui.view.ToggleIconRow

@Composable
fun ScoreToTablatureScreen(
    modifier: Modifier = Modifier
) {
    ScoreToTablatureContent(modifier)
}
@Composable
fun ScoreToTablatureContent(
    modifier: Modifier = Modifier
) {
    val guitar = Guitar.default()
    var currentNote by remember { mutableStateOf(ScoreNote.E2) }
    val selectedDecorationIndex = remember { mutableStateOf(1) }
    val decorator = remember { mutableStateOf(ScoreNoteDecoration.NATURAL) }
    Column(modifier.padding(16.dp)) {
        Score(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
                .testTag("score"),
            currentNote = currentNote,
            showSupplementaryLines = false,
            onUpdateNoteIndex = { currentNote = ScoreNote.getByIndex(it) ?: ScoreNote.E2 },
            noteDecoration = decorator.value
        )
        val note = currentNote.index.toNote(decorator.value)
        Tablature(
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
                text = buildString {
                    append(stringResource(id = R.string.current_note))
                    append(note?.toString().orEmpty())
                }
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
                        description = stringResource(id = R.string.flat_button)
                    ),
                    Icon(
                        painter = painterResource(id = R.drawable.ic_natural_note),
                        description = stringResource(id = R.string.natural_button)
                    ),
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sharp_black),
                        description = stringResource(id = R.string.sharp_button)
                    )
                )
            )
        }
        AdvertView(
            modifier = Modifier.padding(top = 16.dp),
            unitId = R.string.admob_home_banner_id
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewComposableConverter() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ScoreToTablatureContent(
                modifier = Modifier
            )
        }
    }
}