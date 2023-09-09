package com.dosei.music.scoreconverter.feature.converter.tablature

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.arpeggio.Score
import com.dosei.music.arpeggio.ScoreNote
import com.dosei.music.arpeggio.ScoreNoteDecoration
import com.dosei.music.arpeggio.Tablature
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.main.Feature
import com.dosei.music.scoreconverter.main.toNote
import com.dosei.music.scoreconverter.toolbox.AdvertView
import com.dosei.music.scoreconverter.ui.theme.AppTheme

@Composable
fun ScoreToTablatureScreen(
    modifier: Modifier = Modifier
) {
    ScoreToTablatureContent(modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreToTablatureContent(
    modifier: Modifier = Modifier
) {
    val guitar = Guitar.default()
    var currentNote by remember { mutableStateOf(ScoreNote.E2) }
    var decorator by remember { mutableStateOf(ScoreNoteDecoration.NATURAL) }
    val interactionSource = remember { MutableInteractionSource() }
    val showHelp by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = Feature.ScoreToTablature.nameRes)) },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_help_outline_24),
                            contentDescription = stringResource(R.string.action_help)
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(16.dp, 16.dp, 16.dp, 16.dp)
    ) { padding ->
        Column(modifier.padding(padding)) {
            val note = currentNote.index.toNote(decorator)
            Text(
                fontSize = 18.sp,
                text = stringResource(id = R.string.current_note, note?.toString().orEmpty()),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
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
            Spacer(modifier = Modifier.weight(1f))
            Score(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .testTag("score")
                    .clickable(interactionSource = interactionSource, indication = null) {
                        decorator = when (decorator) {
                            ScoreNoteDecoration.FLAT -> ScoreNoteDecoration.NATURAL
                            ScoreNoteDecoration.NATURAL -> ScoreNoteDecoration.SHARP
                            ScoreNoteDecoration.SHARP -> ScoreNoteDecoration.FLAT
                        }
                    },
                currentNote = currentNote,
                showSupplementaryLines = false,
                onUpdateNoteIndex = { currentNote = ScoreNote.getByIndex(it) ?: ScoreNote.E2 },
                noteDecoration = decorator
            )
            Spacer(modifier = Modifier.height(16.dp))
            AdvertView(
                modifier = Modifier.padding(top = 16.dp),
                unitId = R.string.admob_home_banner_id
            )
        }
    }

}

@Preview(showBackground = true, locale = "pt-rBR")
@Preview(showBackground = true, locale = "pt-rBR", uiMode = Configuration.UI_MODE_NIGHT_YES)
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