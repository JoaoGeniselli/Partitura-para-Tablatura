package com.dosei.music.scoreconverter.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.BuildConfig
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.main.Feature
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    currentFeature: Feature?,
    onSelect: (Feature) -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val features = remember { Feature.values() }
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(24.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 28.dp),
                    style = MaterialTheme.typography.titleSmall,
                    text = stringResource(id = R.string.app_name)
                )
                Spacer(Modifier.height(16.dp))
                features.forEach { item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        label = { Text(stringResource(item.nameRes)) },
                        selected = item == currentFeature,
                        onClick = {
                            scope.launch { drawerState.close() }
                            onSelect(item)
                        }
                    )
                }
                Spacer(Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(horizontal = 28.dp),
                    text = buildString {
                        append("v")
                        append(BuildConfig.VERSION_NAME)
                    }
                )
                Spacer(Modifier.height(16.dp))
            }
        },
        content = content
    )
}

@Preview(showBackground = true, locale = "pt-rBR")
@Composable
private fun PreviewAppDrawer() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        AppDrawer(
            modifier = Modifier,
            drawerState = DrawerState(DrawerValue.Open),
            currentFeature = Feature.ScoreToTablature,
            onSelect = {},
            content = {}
        )
    }
}
