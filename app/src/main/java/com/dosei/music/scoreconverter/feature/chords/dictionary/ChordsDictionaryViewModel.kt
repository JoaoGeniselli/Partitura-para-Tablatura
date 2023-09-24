package com.dosei.music.scoreconverter.feature.chords.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dosei.music.scoreconverter.feature.chords.dictionary.data.ChordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ChordsDictionaryViewModel(
    repository: ChordsRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    val state = combine(
        _searchQuery,
        repository.allChords.onStart { emit(emptyList()) }
    ) { query, chords ->

        val filteredChords = if (query.isEmpty()) {
            chords
        } else {
            chords.filter { it.name.contains(query) }
        }

        ChordsDictionaryState(
            content = filteredChords,
        )
    }

    fun onSearch(query: String) {
        viewModelScope.launch { _searchQuery.emit(query) }
    }

}