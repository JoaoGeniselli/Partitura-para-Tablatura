package com.dosei.music.scoreconverter.transposer

import NoteModifier
import Transpose
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dosei.music.scoreconverter.toolbox.BeautifySong
import com.dosei.music.scoreconverter.toolbox.CopyToClipboard
import com.dosei.music.scoreconverter.toolbox.SingleLiveEvent

class TransposerViewModel(
    private val copyToClipboard: CopyToClipboard,
    private val beautifySong: BeautifySong,
    private val transpose: Transpose
) : ViewModel() {

    private val _toastMessage = SingleLiveEvent<Unit>()
    val toastMessage: LiveData<Unit> get() = _toastMessage

    fun onBeautify(song: AnnotatedString): AnnotatedString = beautifySong(song)

    fun onCopy(song: AnnotatedString) {
        copyToClipboard("Song", song)
        _toastMessage.value = Unit
    }

    fun onTranspose(song: AnnotatedString, semitones: Int): AnnotatedString {
        val transposed = transpose(
            semitones = semitones,
            source = song,
            preferredModifier = NoteModifier.AUTO
        )
        return onBeautify(AnnotatedString(transposed))
    }

}