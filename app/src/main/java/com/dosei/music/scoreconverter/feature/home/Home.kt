package com.dosei.music.scoreconverter.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.main.Feature
import com.dosei.music.scoreconverter.ui.theme.AppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSelectFeature: (Feature) -> Unit,
    onAboutClicked: () -> Unit
) {
    HomeContent(
        modifier = modifier,
        onSelectFeature = onSelectFeature,
        onAboutClicked = onAboutClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onSelectFeature: (Feature) -> Unit,
    onAboutClicked: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MediumTopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        },
        contentWindowInsets = WindowInsets(0.dp, 16.dp, 0.dp, 16.dp)
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(Feature.values()) { feature ->
                ListItem(
                    modifier = Modifier.clickable { onSelectFeature(feature) },
                    headlineContent = { Text(text = stringResource(feature.nameRes)) },
                )
                Divider(Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Preview(showBackground = true, locale = "pt-rBR")
@Preview(showBackground = true, locale = "pt-rBR", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewHome() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            HomeContent(
                modifier = Modifier,
                onSelectFeature = {},
                onAboutClicked = {}
            )
        }
    }
}
