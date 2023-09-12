package com.dosei.music.scoreconverter.feature.converter.tablature

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dosei.music.scoreconverter.R

@Composable
fun ScoreToTablatureHelp(modifier: Modifier = Modifier) {
    ScoreToTablatureHelpContent(modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreToTablatureHelpContent(modifier: Modifier = Modifier) {
    val bullet = "\u2022"
    val items = stringArrayResource(id = R.array.score_to_tablature_help)
    val paragraphStyle = ParagraphStyle(
        textIndent = TextIndent(restLine = 15.sp),
    )
    val text = buildAnnotatedString {
        items.forEach { item ->
            withStyle(paragraphStyle) {
                append(bullet)
                append("\t\t")
                append(item)
            }
        }
    }
    PlainTooltipBox(modifier = modifier, tooltip = { Text(text) }) {

    }
}

@Preview(locale = "pt-rBR")
@Composable
private fun PreviewScoreToTablatureHelp() {
    ScoreToTablatureHelp(
        modifier = Modifier.width(300.dp)
    )
}
