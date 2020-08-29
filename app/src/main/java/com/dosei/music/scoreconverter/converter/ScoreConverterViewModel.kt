package com.dosei.music.scoreconverter.converter

import androidx.lifecycle.*
import com.dosei.music.scoreconverter.*

class ScoreConverterViewModel : ViewModel(), LifecycleObserver {

    private val _currentNote = SingleLiveEvent<CurrentNote>()
    val currentNote: LiveData<CurrentNote> get() = _currentNote

    private val _progressMax = MutableLiveData<Int>()
    val progressMax: LiveData<Int> get() = _progressMax

    private var currentNotePosition: Int = 0

    private val guitar = Guitar.default()
    private val calculator = CalculateGuitarRangeUseCase()

    private var allNotes: List<OctavedNote> = listOf()

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
        currentNotePosition = updatedPosition
        _currentNote.value = allNotes[currentNotePosition].toCurrentNote()
    }

    private fun OctavedNote.toCurrentNote(): CurrentNote {
        val positions = guitar.tuning.run {
            GuitarPositions(
                string1 = string1.positionOf(this@toCurrentNote),
                string2 = string2.positionOf(this@toCurrentNote),
                string3 = string3.positionOf(this@toCurrentNote),
                string4 = string4.positionOf(this@toCurrentNote),
                string5 = string5.positionOf(this@toCurrentNote),
                string6 = string6.positionOf(this@toCurrentNote)
            )
        }
        return CurrentNote(
            name = name,
            scorePosition = allNotes.lastIndex - allNotes.indexOf(this),
            tablaturePositions = positions
        )
    }

    fun onSavedIndexRetrieved(index: Int) {
        currentNotePosition = index
    }

    fun onProgressUpdate(updatedIndex: Int) = updateCurrentNote(updatedIndex)
}