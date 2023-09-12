package com.dosei.music.scoreconverter.feature.chords.transposer

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.main.Feature
import com.dosei.music.scoreconverter.toolbox.AdvertView
import com.dosei.music.scoreconverter.toolbox.formatSemitonesToTones
import com.dosei.music.scoreconverter.ui.view.Icon
import com.dosei.music.scoreconverter.ui.view.ToggleIconRow
import org.koin.java.KoinJavaComponent.get

@Composable
fun TransposerLoader(
    modifier: Modifier = Modifier
) {
    val viewModel: TransposerViewModel = get(TransposerViewModel::class.java)
    val song = remember { mutableStateOf(TextFieldValue("")) }
    val semitones = remember { mutableStateOf(0) }

    Transposer(
        modifier = modifier,
        song = song.value,
        semitones = semitones.value,
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
fun Transposer(
    modifier: Modifier = Modifier,
    song: TextFieldValue,
    semitones: Int,
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
                floatingActionButton = {
                    Badge {

                    }
                    FloatingActionButton(
                        onClick = onTranspose,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = stringResource(R.string.action_transpose)
                        )
                    }
                },
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
                    IconButton(onClick = onRemoveSemitone) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                            contentDescription = stringResource(id = R.string.transposer_remove_semitone_button)
                        )
                    }
                    IconButton(onClick = onAddSemitone) {
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
            AdvertView(unitId = R.string.admob_transposer_banner_id)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                textStyle = LocalTextStyle
                    .current
                    .copy(fontFamily = FontFamily.Monospace),
                label = { Text(text = stringResource(R.string.chords_lyrics)) },
                placeholder = {
                    Text(
                        fontFamily = FontFamily.Monospace,
                        text = stringResource(id = R.string.transposer_your_chords_here)
                    )
                },
                value = song,
                onValueChange = { content -> onChangeSong(content) },
                supportingText = {
                    Text(
                        pluralStringResource(
                            id = R.plurals.tones,
                            count = (semitones / 2),
                            semitones.formatSemitonesToTones()
                        )
                    )
                }
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, locale = "pt-rBR")
@Composable
private fun PreviewTransposer() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Transposer(
            modifier = Modifier,
            song = TextFieldValue(),
            semitones = 0,
            onChangeSong = {},
            onAddSemitone = {},
            onRemoveSemitone = {},
            onBeautify = {},
            onCopy = {},
            onTranspose = {}
        )
    }
}