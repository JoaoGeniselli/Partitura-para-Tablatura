package com.dosei.music.scoreconverter.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.R

@Composable
fun FeatureMenu(
    columnScope: ColumnScope,
    activeFeature: Feature,
    features: List<Feature> = Feature.values().toList(),
    onClickFeature: (Feature) -> Unit
) {
    columnScope.run {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .padding(bottom = 16.dp)
                .height(42.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().align(Bottom),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h6
            )
        }
        features.forEach { currentFeature ->
            if (activeFeature == currentFeature) {
                ActiveMenuRow(
                    text = stringResource(id = currentFeature.nameRes),
                    onClick = { onClickFeature(currentFeature) }
                )
            } else {
                InactiveMenuRow(
                    text = stringResource(id = currentFeature.nameRes),
                    onClick = { onClickFeature(currentFeature) }
                )
            }
        }
    }
}

@Composable
fun ActiveMenuRow(text: String, onClick: () -> Unit) {
    val color =
        if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
    Row(
        Modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(horizontal = 11.dp, vertical = 2.dp)
            .clickable(onClick = onClick)
            .background(
                shape = MaterialTheme.shapes.small,
                color = color.copy(alpha = .12f)
            )
    ) {
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(horizontal = 11.dp),
            text = text,
            color = color,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun InactiveMenuRow(text: String, onClick: () -> Unit) {
    Row(
        Modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(horizontal = 11.dp, vertical = 2.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(horizontal = 11.dp),
            text = text,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFeatureMenu() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column {
            FeatureMenu(
                this,
                activeFeature = Feature.ChordsDictionary,
                features = Feature.values().toList(),
                onClickFeature = {}
            )
        }
    }
}