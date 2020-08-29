package com.dosei.music.scoreconverter.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dosei.music.scoreconverter.*
import kotlinx.android.synthetic.main.fragment_score_converter.*
import kotlinx.android.synthetic.main.fragment_score_converter.seek_bar
import org.koin.android.viewmodel.ext.android.viewModel

class ScoreConverterFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private val viewModel by viewModel<ScoreConverterViewModel>()
    private lateinit var scoreFragment: ScoreFragment
    private lateinit var tablatureFragment: TablatureFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_score_converter, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initChildFragments()
        initSeekBar()

        viewModel.apply {
            lifecycle.addObserver(this)
            currentNote.observe(
                this@ScoreConverterFragment,
                Observer { updateNote(it) }
            )
            progressMax.observe(
                this@ScoreConverterFragment,
                Observer { seek_bar?.max = it }
            )
            savedInstanceState?.getInt(STATE_KEY_PROGRESS)?.let {
                onSavedIndexRetrieved(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_KEY_PROGRESS, seek_bar?.progress ?: 0)
    }

    private fun initSeekBar() {
        seek_bar?.apply {
            progress = 0
            setOnSeekBarChangeListener(this@ScoreConverterFragment)
        }
    }

    private fun initChildFragments() {
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
    }

    private fun updateNote(note: CurrentNote?) {
        note?.run {
            text_current_note.text = getString(R.string.current_note, name)
            scoreFragment.notePosition = scorePosition
            tablatureFragment.positions = tablaturePositions
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        viewModel.onProgressUpdate(progress)
    }

    override fun onStartTrackingTouch(p0: SeekBar?) = Unit
    override fun onStopTrackingTouch(p0: SeekBar?) = Unit

    companion object {
        private const val STATE_KEY_PROGRESS = "progress"
    }
}