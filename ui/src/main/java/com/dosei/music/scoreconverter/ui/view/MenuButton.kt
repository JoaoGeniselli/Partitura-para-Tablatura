package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dosei.music.scoreconverter.ui.R

@Composable
fun MenuButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = stringResource(R.string.menu))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMenuButton() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        MenuButton(
            modifier = Modifier, {}
        )
    }
}
