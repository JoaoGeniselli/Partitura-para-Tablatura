package com.dosei.music.scoreconverter.converter

import androidx.lifecycle.*
import com.dosei.music.scoreconverter.NoteModifier
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.domain.NotesRepository
import com.dosei.music.scoreconverter.domain.OctavedNote
import com.dosei.music.scoreconverter.domain.PositionsRepository
import com.dosei.music.scoreconverter.toolbox.SingleLiveEvent
import com.dosei.music.scoreconverter.ui.view.ScoreNoteDecoration

class ScoreConverterViewModel(
    private val guitar: Guitar,
    private val notesRepository: NotesRepository,
    private val positionsRepository: PositionsRepository
) : ViewModel(), LifecycleObserver {

    private var allNotes: List<OctavedNote> = listOf()
    private var noteModifier: NoteModifier? = null
    private var currentNotePosition: Int = 0

    private val _currentNote = SingleLiveEvent<CurrentNote>()
    val currentNote: LiveData<CurrentNote> get() = _currentNote

    private val _progressMax = MutableLiveData<Int>()
    val progressMax: LiveData<Int> get() = _progressMax

    private val _noteDecoration = MutableLiveData<ScoreNoteDecoration?>()
    val noteDecoration: LiveData<ScoreNoteDecoration?> get() = _noteDecoration

    private val _sharpHighlight = SingleLiveEvent<Boolean>()
    val sharpHighlight: LiveData<Boolean> get() = _sharpHighlight

    private val _flatHighlight = SingleLiveEvent<Boolean>()
    val flatHighlight: LiveData<Boolean> get() = _flatHighlight

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun init() {
        allNotes = notesRepository.findAllNotes(guitar)
        currentNotePosition = 0
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun resume() {
        _progressMax.value = allNotes.lastIndex
        updateCurrentNote(currentNotePosition)
    }

    fun onSavedIndexRetrieved(index: Int) {
        currentNotePosition = reversedIndex(index)
    }

    fun onSavedModifierRetrieved(modifier: NoteModifier?) {
        noteModifier = modifier
        updateCurrentNote(currentNotePosition)
    }

    private fun updateCurrentNote(updatedPosition: Int) {
        updateNoteModifierIndicators()
        currentNotePosition = updatedPosition
        _currentNote.value = allNotes[currentNotePosition].toCurrentNote()
    }

    private fun updateNoteModifierIndicators() {
        when (noteModifier) {
            NoteModifier.SHARP -> {
                _sharpHighlight.value = true
                _flatHighlight.value = false
            }
            NoteModifier.FLAT -> {
                _sharpHighlight.value = false
                _flatHighlight.value = true
            }
            else -> {
                _sharpHighlight.value = false
                _flatHighlight.value = false
            }
        }
        _noteDecoration.value = noteModifier.toNoteDecoration()
    }

    private fun OctavedNote.toCurrentNote(): CurrentNote {
        val adjustedNote = copy(modifier = noteModifier)
        val positions = positionsRepository.findPositions(adjustedNote, guitar)
        return CurrentNote(
            name = adjustedNote.name,
            scorePosition = reversedIndex(allNotes.indexOf(this)),
            tablaturePositions = positions
        )
    }

    private fun reversedIndex(index: Int) = allNotes.lastIndex - index

    fun onSharpClicked() {
        noteModifier = if (noteModifier == NoteModifier.SHARP) null else NoteModifier.SHARP
        updateCurrentNote(currentNotePosition)
    }

    fun onFlatClicked() {
        noteModifier = if (noteModifier == NoteModifier.FLAT) null else NoteModifier.FLAT
        updateCurrentNote(currentNotePosition)
    }

    fun onScorePositionUpdated(updatedIndex: Int) {
        val reversedPosition = reversedIndex(updatedIndex)
        updateCurrentNote(reversedPosition)
    }
}