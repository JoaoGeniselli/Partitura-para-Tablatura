package com.dosei.music.scoreconverter.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.dosei.music.scoreconverter.*
import kotlinx.android.synthetic.main.fragment_score_converter.*
import kotlinx.android.synthetic.main.fragment_score_converter.seek_bar

class ScoreConverterFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var scoreFragment: ScoreFragment
    private lateinit var tablatureFragment: TablatureFragment
    private val notes = initNaturalNotes()
    private lateinit var currentNote: GuitarNote

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_score_converter, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        scoreFragment = ScoreFragment()
        tablatureFragment = TablatureFragment()

        activity?.supportFragmentManager?.run {
            beginTransaction()
                .replace(R.id.notes_container, scoreFragment, "Score")
                .commit()

            beginTransaction()
                .replace(R.id.tablature_container, tablatureFragment, "Tablature")
                .commit()
        }

        seek_bar?.apply {
            max = notes.size - 1
            progress = 0
            setOnSeekBarChangeListener(this@ScoreConverterFragment)
        }

        currentNote = notes.first()
    }

    override fun onResume() {
        super.onResume()
        updateCurrentNote(currentNote)
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val selectedNote = notes[seek_bar.progress]
        updateCurrentNote(selectedNote)
    }

    private fun updateCurrentNote(note: GuitarNote) {
        currentNote = note
        text_current_note.text = getString(R.string.current_note, note.name)
        scoreFragment.notePosition = notes.lastIndex - notes.indexOf(note)
        tablatureFragment.positions = note.positions
    }

    override fun onStartTrackingTouch(p0: SeekBar?) = Unit
    override fun onStopTrackingTouch(p0: SeekBar?) = Unit
}