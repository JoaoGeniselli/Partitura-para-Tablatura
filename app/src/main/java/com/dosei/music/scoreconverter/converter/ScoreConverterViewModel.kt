package com.dosei.music.scoreconverter.converter

import androidx.lifecycle.*
import com.dosei.music.scoreconverter.GuitarNote
import com.dosei.music.scoreconverter.SingleLiveEvent
import com.dosei.music.scoreconverter.initNaturalNotes

class ScoreConverterViewModel : ViewModel(), LifecycleObserver {

    private val _currentNote = SingleLiveEvent<CurrentNote>()
    val currentNote: LiveData<CurrentNote> get() = _currentNote

    private val _progressMax = MutableLiveData<Int>()
    val progressMax: LiveData<Int> get() = _progressMax

    private lateinit var allNotes: List<GuitarNote>
    private var currentNoteIndex = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun init() {
        allNotes = initNaturalNotes()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun resume() {
        _progressMax.value = allNotes.lastIndex
        updateCurrentNote(currentNoteIndex)
    }

    private fun updateCurrentNote(updatedIndex: Int) {
        currentNoteIndex = updatedIndex
        _currentNote.value = allNotes[currentNoteIndex].toCurrentNote()
    }

    private fun GuitarNote.toCurrentNote() = CurrentNote(
        name = name,
        scorePosition = allNotes.lastIndex - allNotes.indexOf(this),
        tablaturePositions = positions
    )

    fun onSavedIndexRetrieved(index: Int) {
        currentNoteIndex = index
    }

    fun onProgressUpdate(updatedIndex: Int) = updateCurrentNote(updatedIndex)
}