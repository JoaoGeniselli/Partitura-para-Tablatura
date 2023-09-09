package com.dosei.music.scoreconverter.screen.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.R

@Composable
fun ComposableAbout(version: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = stringResource(id = R.string.version_name_label, version),
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            text = stringResource(id = R.string.about_content),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableAbout() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableAbout(
            version = "1.0.0"
        )
    }
}