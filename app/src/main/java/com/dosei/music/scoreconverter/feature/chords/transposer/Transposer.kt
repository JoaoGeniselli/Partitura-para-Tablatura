package com.dosei.music.scoreconverter.feature.chords.transposer

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.dosei.music.scoreconverter.toolbox.AdvertView
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
        toastMessage = null,
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
    toastMessage: Unit?,
    semitones: Int,
    onChangeSong: (TextFieldValue) -> Unit,
    onAddSemitone: () -> Unit,
    onRemoveSemitone: () -> Unit,
    onBeautify: () -> Unit,
    onCopy: () -> Unit,
    onTranspose: () -> Unit,
) {
    Column(modifier.padding(16.dp)) {
        AdvertView(unitId = R.string.admob_transposer_banner_id)

        OutlinedTextField(
            modifier = Modifier
                .padding(top = 8.dp)
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
            onValueChange = { content -> onChangeSong(content) }
        )

        Text(
            modifier = Modifier.align(Alignment.End),
            fontSize = 16.sp,
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    if (semitones >= 0) append('+')
                    append(semitones.toString())
                }
                append(' ')
                append(stringResource(id = R.string.transposer_semitones_diff))
            }
        )

        ToggleIconRow(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            selectedIndex = -1,
            onSelectIndex = {
                when (it) {
                    0 -> onBeautify()
                    1 -> onCopy()
                    2 -> onRemoveSemitone()
                    3 -> onAddSemitone()
                }
            },
            cornerRadius = 4.dp,
            iconSize = 24.dp,
            icons = listOf(
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_auto_fix_high_24),
                    description = stringResource(id = R.string.transposer_beautify_button)
                ),
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_content_copy_24),
                    description = stringResource(id = R.string.transposer_copy_button)
                ),
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                    description = stringResource(id = R.string.transposer_remove_semitone_button)
                ),
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    description = stringResource(id = R.string.transposer_add_semitone_button)
                ),
            )
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = onTranspose
        ) {
            Text(
                fontSize = 18.sp,
                text = stringResource(id = R.string.transposer_transpose_button)
            )
        }
    }

    toastMessage?.let {
        Toast.makeText(LocalContext.current, "Value Copied!", Toast.LENGTH_LONG).show()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTransposer() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Transposer(
            modifier = Modifier,
            song = TextFieldValue(),
            toastMessage = null,
            semitones = 3,
            onChangeSong = {},
            onAddSemitone = {},
            onRemoveSemitone = {},
            onBeautify = {},
            onCopy = {},
            onTranspose = {}
        )
    }
}