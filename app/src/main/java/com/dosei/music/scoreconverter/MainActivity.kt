package com.dosei.music.scoreconverter

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private lateinit var scoreFragment: ScoreFragment
    private lateinit var tablatureFragment: TablatureFragment

    private val notes = initNaturalNotes()
    private lateinit var currentNote: GuitarNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreFragment = ScoreFragment()
        tablatureFragment = TablatureFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.notes_container, scoreFragment, "Score")
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.tablature_container, tablatureFragment, "Tablature")
            .commit()

        seek_bar.max = notes.size - 1
        seek_bar.progress = 0
        seek_bar.setOnSeekBarChangeListener(this)

        currentNote = notes.first()
    }

    override fun onResume() {
        super.onResume()
        updateCurrentNote(currentNote)
    }

    private fun updateCurrentNote(note: GuitarNote) {
        currentNote = note
        text_current_note.text = getString(R.string.current_note, note.name)
        scoreFragment.notePosition = notes.lastIndex - notes.indexOf(note)
        tablatureFragment.positions = note.positions
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val selectedNote = notes[seek_bar.progress]
        updateCurrentNote(selectedNote)
    }

    override fun onStartTrackingTouch(p0: SeekBar?) = Unit
    override fun onStopTrackingTouch(p0: SeekBar?) = Unit
}
