package com.dosei.music.scoreconverter.feature.chords.dictionary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.arpeggio.ChordDiagram
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.feature.chords.dictionary.data.Chords
import com.dosei.music.scoreconverter.ui.view.MenuButton

@Composable
fun ChordsDictionaryScreen(
    modifier: Modifier = Modifier,
    viewModel: ChordsDictionaryViewModel,
    onMenuClick: () -> Unit
) {
    val state by viewModel.state.collectAsState(initial = ChordsDictionaryState())
    ChordsDictionaryContent(
        modifier,
        state,
        viewModel::onSearch,
        onMenuClick,
    )
}

@Composable
private fun ChordsDictionaryContent(
    modifier: Modifier,
    state: ChordsDictionaryState,
    onSearch: (String) -> Unit,
    onMenuClick: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(8.dp, 16.dp, 8.dp, 16.dp)
    ) { padding ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Search(onMenuClick, onSearch)
            Spacer(modifier = Modifier.height(8.dp))
            GuitarThumbnailTheme {
                LazyVerticalGrid(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = spacedBy(8.dp),
                    horizontalArrangement = spacedBy(8.dp),
                    columns = GridCells.Fixed(2),
                    contentPadding = padding
                ) {
                    items(state.content) { chord ->
                        ChordDiagram(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(.85f),
                            name = chord.name,
                            components = chord.components
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Search(
    onMenuClick: () -> Unit,
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val history = remember { mutableListOf<String>() }
    var isSearchActive by remember { mutableStateOf(false) }
    SearchBar(
        modifier = Modifier,
        leadingIcon = { MenuButton(onClick = onMenuClick) },
        trailingIcon = if (query.isNotEmpty()) {
            {
                IconButton(onClick = { onSearch("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
        } else null,
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            onSearch(it)
            isSearchActive = false
            history.add(it)
        },
        active = isSearchActive,
        onActiveChange = { isSearchActive = it },
        placeholder = { Text(stringResource(R.string.search_chords)) },
        windowInsets = WindowInsets(top = 8.dp)
    ) {
        history.reversed().forEach { historyItem ->
            ListItem(
                modifier = Modifier.clickable {
                    query = historyItem
                    isSearchActive = false
                },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_history_24),
                        contentDescription = stringResource(R.string.history_item)
                    )
                },
                headlineContent = { Text(text = historyItem) }
            )
        }
    }
}

@Preview(showBackground = true, locale = "pt-rBR")
@Composable
private fun PreviewChordsDictionary() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        ChordsDictionaryContent(
            modifier = Modifier,
            state = ChordsDictionaryState(
                content = Chords.all.take(5)
            ),
            {}, {}
        )
    }
}