package com.dosei.music.scoreconverter.converter

import android.util.Log
import androidx.lifecycle.*
import com.dosei.music.scoreconverter.*
import com.dosei.music.scoreconverter.domain.Guitar
import com.dosei.music.scoreconverter.ui.view.GuitarPositions
import com.dosei.music.scoreconverter.domain.OctavedNote
import com.dosei.music.scoreconverter.toolbox.SingleLiveEvent
import com.dosei.music.scoreconverter.ui.view.ScoreNoteDecoration

class ScoreConverterViewModel : ViewModel(), LifecycleObserver {

    private val _currentNote =
        SingleLiveEvent<CurrentNote>()
    val currentNote: LiveData<CurrentNote> get() = _currentNote

    private val _progressMax = MutableLiveData<Int>()
    val progressMax: LiveData<Int> get() = _progressMax

    private val _noteDecoration = MutableLiveData<ScoreNoteDecoration?>()
    val noteDecoration: LiveData<ScoreNoteDecoration?> get() = _noteDecoration

    private var currentNotePosition: Int = 0

    private val guitar = Guitar.default()
    private val calculator = CalculateGuitarRangeUseCase()

    private var allNotes: List<OctavedNote> = listOf()

    private var noteModifier: NoteModifier? = null

    private val _sharpHighlight =
        SingleLiveEvent<Boolean>()
    val sharpHighlight: LiveData<Boolean> get() = _sharpHighlight

    private val _flatHighlight =
        SingleLiveEvent<Boolean>()
    val flatHighlight: LiveData<Boolean> get() = _flatHighlight

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun init() {
        allNotes = calculator.generateAllNotes(guitar)
        currentNotePosition = 0
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun resume() {
        _progressMax.value = allNotes.lastIndex
        updateCurrentNote(currentNotePosition)
    }

    private fun updateCurrentNote(updatedPosition: Int) {
        when(noteModifier) {
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
        currentNotePosition = updatedPosition
        _currentNote.value = allNotes[currentNotePosition].toCurrentNote()
        _noteDecoration.value = when (noteModifier) {
            NoteModifier.FLAT -> ScoreNoteDecoration.FLAT
            NoteModifier.SHARP -> ScoreNoteDecoration.SHARP
            else -> null
        }
    }

    private fun OctavedNote.toCurrentNote(): CurrentNote {
        val adjustedNote = this.copy(modifier = noteModifier)
        val positions = guitar.tuning.run {
            GuitarPositions(
                string1 = string1.positionOf(adjustedNote),
                string2 = string2.positionOf(adjustedNote),
                string3 = string3.positionOf(adjustedNote),
                string4 = string4.positionOf(adjustedNote),
                string5 = string5.positionOf(adjustedNote),
                string6 = string6.positionOf(adjustedNote)
            )
        }
        return CurrentNote(
            name = adjustedNote.name,
            scorePosition = allNotes.lastIndex - allNotes.indexOf(this),
            tablaturePositions = positions
        )
    }

    fun onSavedIndexRetrieved(index: Int) {
        currentNotePosition = allNotes.lastIndex - index
    }

    fun onSharpClicked() {
        noteModifier = if (noteModifier == NoteModifier.SHARP) null else NoteModifier.SHARP
        updateCurrentNote(currentNotePosition)
    }

    fun onFlatClicked() {
        noteModifier = if (noteModifier == NoteModifier.FLAT) null else NoteModifier.FLAT
        updateCurrentNote(currentNotePosition)
    }

    fun onScorePositionUpdated(updatedIndex: Int) {
        val reversedPosition = allNotes.lastIndex - updatedIndex
        updateCurrentNote(reversedPosition)
    }

    fun onSavedModifierRetrieved(modifier: NoteModifier?) {
        noteModifier = modifier
        updateCurrentNote(currentNotePosition)
    }
}