package com.dosei.music.scoreconverter.feature.chords.transposer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.main.Feature
import com.dosei.music.scoreconverter.toolbox.AdvertView
import org.koin.java.KoinJavaComponent.get

@Composable
fun TransposerScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: TransposerViewModel = get(TransposerViewModel::class.java)
    val song = remember { mutableStateOf(TextFieldValue("")) }
    val semitones = remember { mutableStateOf(0) }

    TransposerScreenContent(
        modifier = modifier,
        song = song.value,
        onChangeSong = { song.value = it },
        onAddSemitone = { semitones.value += 1 },
        onRemoveSemitone = { semitones.value -= 1 },
        onBeautify = {
            val formatted = viewModel.onBeautify(song.value.annotatedString)
            song.value = song.value.copy(annotatedString = formatted)
        },
        onCopy = { viewModel.onCopy(song.value.annotatedString) },
        onTranspose = {
            val transposed = viewModel.onTranspose(song.value.annotatedString, semitones.value)
            song.value = song.value.copy(annotatedString = transposed)
            semitones.value = 0
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransposerScreenContent(
    modifier: Modifier = Modifier,
    song: TextFieldValue,
    onChangeSong: (TextFieldValue) -> Unit,
    onAddSemitone: () -> Unit,
    onRemoveSemitone: () -> Unit,
    onBeautify: () -> Unit,
    onCopy: () -> Unit,
    onTranspose: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = Feature.Transposer.nameRes)) })
        },
        contentWindowInsets = WindowInsets(16.dp, 16.dp, 16.dp, 16.dp),
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = onBeautify) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_auto_fix_high_24),
                            contentDescription = stringResource(id = R.string.transposer_beautify_button)
                        )
                    }
                    IconButton(onClick = onCopy) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_content_copy_24),
                            contentDescription = stringResource(id = R.string.transposer_copy_button)
                        )
                    }
                    IconButton(
                        onClick = { onRemoveSemitone(); onTranspose() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                            contentDescription = stringResource(id = R.string.transposer_remove_semitone_button)
                        )
                    }
                    IconButton(onClick = { onAddSemitone(); onTranspose() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_add_24),
                            contentDescription = stringResource(id = R.string.transposer_add_semitone_button)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier.padding(padding)) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                textStyle = LocalTextStyle
                    .current
                    .copy(fontFamily = FontFamily.Monospace),
                placeholder = {
                    Text(
                        fontFamily = FontFamily.Monospace,
                        text = stringResource(id = R.string.transposer_your_chords_here)
                    )
                },
                value = song,
                onValueChange = { content -> onChangeSong(content) },
            )
            Spacer(Modifier.height(8.dp))
            AdvertView(unitId = R.string.admob_transposer_banner_id)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, locale = "pt-rBR")
@Composable
private fun PreviewTransposer() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        TransposerScreenContent(
            modifier = Modifier,
            song = TextFieldValue(),
            onChangeSong = {},
            onAddSemitone = {},
            onRemoveSemitone = {},
            onBeautify = {},
            onCopy = {},
            onTranspose = {}
        )
    }
}