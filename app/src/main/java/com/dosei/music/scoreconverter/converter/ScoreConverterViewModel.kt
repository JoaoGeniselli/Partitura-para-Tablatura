package com.dosei.music.scoreconverter.converter

import androidx.lifecycle.*
import com.dosei.music.scoreconverter.*

class ScoreConverterViewModel(
    private val noteRepository: NoteRepository
) : ViewModel(), LifecycleObserver {

    private val _currentNote = SingleLiveEvent<CurrentNote>()
    val currentNote: LiveData<CurrentNote> get() = _currentNote

    private val _progressMax = MutableLiveData<Int>()
    val progressMax: LiveData<Int> get() = _progressMax

    private lateinit var allNotes: List<OctavedNote>
    private var currentNoteIndex = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun init() {
        allNotes = noteRepository.findAllNotes()
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

    private fun OctavedNote.toCurrentNote() = CurrentNote(
        name = "${note.name}${octave}",
        scorePosition = allNotes.lastIndex - allNotes.indexOf(this),
        tablaturePositions = noteRepository.notePositions(this)
    )

    fun onSavedIndexRetrieved(index: Int) {
        currentNoteIndex = index
    }

    fun onProgressUpdate(updatedIndex: Int) = updateCurrentNote(updatedIndex)
}