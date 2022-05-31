package com.dosei.music.scoreconverter.transposer

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.toolbox.AdvertView
import com.dosei.music.scoreconverter.ui.view.Icon
import com.dosei.music.scoreconverter.ui.view.ScoreNoteDecoration
import com.dosei.music.scoreconverter.ui.view.ToggleIconRow

@Composable
fun Transposer(
    modifier: Modifier = Modifier,
    song: AnnotatedString,
    toastMessage: String?,
    semitones: Int,
    onChangeSong: (AnnotatedString) -> Unit,
    onAddSemitone: () -> Unit,
    onRemoveSemitone: () -> Unit,
    onBeautify: () -> Unit,
    onCopy: () -> Unit
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
            value = TextFieldValue(annotatedString = song),
            onValueChange = { content -> onChangeSong(content.annotatedString) }
        )

        Text(
            modifier = Modifier.align(Alignment.End),
            fontSize = 16.sp,
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    if (semitones >=  0) append('+')
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
            onClick = { /*TODO*/ }
        ) {
            Text(
                fontSize = 18.sp,
                text = stringResource(id = R.string.transposer_transpose_button)
            )
        }
    }

    toastMessage?.let {
        Toast.makeText(LocalContext.current, it, Toast.LENGTH_LONG).show()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTransposer() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Transposer(
            modifier = Modifier,
            song = AnnotatedString(""),
            toastMessage = null,
            semitones = 3,
            onChangeSong = {},
            onAddSemitone = {},
            onRemoveSemitone = {},
            onBeautify = {},
            onCopy = {}
        )
    }
}