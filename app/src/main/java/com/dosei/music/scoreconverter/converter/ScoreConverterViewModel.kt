package com.dosei.music.scoreconverter.converter

import androidx.lifecycle.*
import com.dosei.music.scoreconverter.*

class ScoreConverterViewModel(
    private val noteRepository: NoteRepository
) : ViewModel(), LifecycleObserver {

    private val _currentNote = SingleLiveEvent<CurrentNote>()
    val currentNote: LiveData<CurrentNote> get() = _currentNote

    private val _progressRange = MutableLiveData<IntRange>()
    val progressRange: LiveData<IntRange> get() = _progressRange

    private lateinit var allNotesRange: IntRange
    private var currentNotePosition: Int = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun init() {
        allNotesRange = noteRepository.findAllNotesRange()
        currentNotePosition = allNotesRange.first
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun resume() {
        _progressRange.value = noteRepository.findAllNotesRange()
        updateCurrentNote(currentNotePosition)
    }

    private fun updateCurrentNote(updatedPosition: Int) {
//        currentNotePosition = updatedPosition
//        _currentNote.value = allNotes[currentNotePosition].toCurrentNote()
    }

//    private fun toCurrentNote() = CurrentNote(
//        name = "${note.name}${octave}",
//        scorePosition = allNotes.lastIndex - allNotes.indexOf(this),
//        tablaturePositions = noteRepository.notePositions(this)
//    )

    fun onSavedIndexRetrieved(index: Int) {
        currentNotePosition = index
    }

    fun onProgressUpdate(updatedIndex: Int) = updateCurrentNote(updatedIndex)
}