package com.dosei.music.scoreconverter

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private lateinit var scoreFragment: ScoreFragment
    private lateinit var tablatureFragment: TablatureFragment

    private val notes = initNaturalNotes()

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
    }

    override fun onStart() {
        super.onStart()
        scoreFragment.notePosition = 5
        tablatureFragment.positions = GuitarPositions(
            chord2 = 0,
            chord3 = 4,
            chord4 = 9,
            chord5 = 14,
            chord6 = 19
        )
        text_current_note.text = "Nota Atual: B3"
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val currentNote = notes[seek_bar.progress]
        text_current_note.text = "Nota Atual: ${currentNote.name}"
        scoreFragment.notePosition = notes.size - seek_bar.progress - 1
        tablatureFragment.positions = currentNote.positions
    }

    override fun onStartTrackingTouch(p0: SeekBar?) = Unit
    override fun onStopTrackingTouch(p0: SeekBar?) = Unit
}
