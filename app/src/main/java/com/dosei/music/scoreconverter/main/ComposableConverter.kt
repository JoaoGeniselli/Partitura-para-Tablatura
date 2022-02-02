package com.dosei.music.scoreconverter.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.ui.view.ComposableScore
import com.dosei.music.scoreconverter.ui.view.ComposableTablature

@Composable
fun ComposableConverter(modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp)) {
        ComposableScore()
        ComposableTablature()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComposableConverter() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ComposableConverter(
            modifier = Modifier
        )
    }
}