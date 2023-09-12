package com.dosei.music.scoreconverter.feature.chords.dictionary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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

@Composable
fun ChordsDictionaryScreen(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }
    val chords = remember { Chords.all }
    val filteredChords by remember(chords, searchQuery) {
        derivedStateOf {
            if (searchQuery.isEmpty()) {
                chords
            } else {
                chords.filter { it.name.contains(searchQuery, ignoreCase = true) }
            }
        }
    }
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(8.dp, 16.dp, 8.dp, 16.dp)
    ) { padding ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Search(searchQuery) { searchQuery = it }
            Spacer(modifier = Modifier.height(8.dp))
            GuitarThumbnailTheme {
                LazyVerticalGrid(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = spacedBy(8.dp),
                    horizontalArrangement = spacedBy(8.dp),
                    columns = GridCells.Fixed(2),
                    contentPadding = padding
                ) {
                    items(filteredChords) { chord ->
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
private fun Search(query: String, onChangeQuery: (String) -> Unit) {
    var isSearchActive by remember { mutableStateOf(false) }
    val history = remember { mutableListOf<String>() }
    SearchBar(
        modifier = Modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.action_search)
            )
        },
        trailingIcon = if (query.isNotEmpty()) {
            {
                IconButton(onClick = { onChangeQuery("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
        } else null,
        query = query,
        onQueryChange = onChangeQuery,
        onSearch = {
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
                    onChangeQuery(historyItem)
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
        ChordsDictionaryScreen(
            modifier = Modifier
        )
    }
}