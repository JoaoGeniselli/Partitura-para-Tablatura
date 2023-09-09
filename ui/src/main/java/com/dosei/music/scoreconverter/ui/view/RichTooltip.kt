package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RichTooltip(
    modifier: Modifier = Modifier,
    subhead: String? = null,
    supportingText: AnnotatedString
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = 3.dp
    ) {
        Column(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(12.dp))
            if (subhead != null) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = subhead,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = supportingText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PreviewRichTooltip() {
    RichTooltip(
        modifier = Modifier.width(250.dp),
        subhead = "Rich tooltip",
        supportingText = AnnotatedString(
            "Rich tooltips brig attention to a particular " +
                    "element of feature what warrants the user's focus."
        )
    )
}
