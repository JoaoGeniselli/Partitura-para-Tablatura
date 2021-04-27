package com.dosei.music.scoreconverter.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.player.AudioPlayer
import com.dosei.music.scoreconverter.ui.tutorial.Tutorial
import com.dosei.music.scoreconverter.ui.view.ScoreFragment
import com.dosei.music.scoreconverter.ui.view.ScoreNoteDecoration
import com.dosei.music.scoreconverter.ui.view.TablatureFragment
import kotlinx.android.synthetic.main.fragment_score_converter.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ScoreConverterFragment : Fragment(), ScoreFragment.OnPositionChangedListener {

    private val viewModel by viewModel<ScoreConverterViewModel>()
    private lateinit var scoreFragment: ScoreFragment
    private lateinit var tablatureFragment: TablatureFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_score_converter, container, false)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_KEY_NOTE_POSITION, scoreFragment.notePosition ?: 0)
        outState.putSerializable(STATE_KEY_NOTE_DECORATION, scoreFragment.noteDecoration)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initChildFragments()
        viewModel.bindToMe(savedInstanceState)
    }

    private fun initChildFragments() {
        scoreFragment = ScoreFragment.newInstance(GUITAR_UPPER_LINES, GUITAR_LOWER_LINES)
        tablatureFragment = TablatureFragment()
        scoreFragment.onPositionChangedListener = this

        insertFragment(R.id.notes_container, scoreFragment, "Score")
        insertFragment(R.id.tablature_container, tablatureFragment, "Tablature")
    }

    private fun insertFragment(container: Int, fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .replace(container, fragment, tag)
                .commit()
        }
    }

    private fun ScoreConverterViewModel.bindToMe(savedInstanceState: Bundle?) {
        lifecycle.addObserver(this)

        sharp_button.setOnClickListener {
            viewModel.onSharpClicked()
        }
        flat_button.setOnClickListener { viewModel.onFlatClicked() }

        currentNote.observe(
            this@ScoreConverterFragment,
            Observer { updateNote(it) }
        )
        progressMax.observe(
            this@ScoreConverterFragment,
            Observer { scoreFragment.maxPosition = it }
        )
        sharpHighlight.observe(
            this@ScoreConverterFragment,
            Observer { updateSharpButtonIcon(it) }
        )
        flatHighlight.observe(
            this@ScoreConverterFragment,
            Observer { updateFlatButtonIcon(it) }
        )
        noteDecoration.observe(
            this@ScoreConverterFragment,
            Observer { scoreFragment.noteDecoration = it }
        )
        showTutorial.observe(
            this@ScoreConverterFragment,
            Observer { showTutorial() }
        )
        savedInstanceState?.lastNotePosition?.let { position ->
            onSavedIndexRetrieved(position)
        }
        savedInstanceState?.lastNoteDecoration?.let { decoration ->
            onSavedModifierRetrieved(decoration.toNoteModifier())
        }
    }

    private fun updateNote(note: CurrentNote?) {
        note?.run {
            text_current_note.text = getString(R.string.current_note, name)
            scoreFragment.notePosition = scorePosition
            tablatureFragment.positions = tablaturePositions
        }
    }

    private fun updateSharpButtonIcon(highlight: Boolean) {
        val updatedIcon = if (highlight) {
            R.drawable.ic_sharp_active
        } else {
            R.drawable.ic_sharp_black
        }
        sharp_button.setImageResource(updatedIcon)
    }

    private fun updateFlatButtonIcon(highlight: Boolean) {
        val updatedIcon = if (highlight) {
            R.drawable.ic_flat_active
        } else {
            R.drawable.ic_flat_black
        }
        flat_button.setImageResource(updatedIcon)
    }

    private fun showTutorial() {
        val noteIndicator = view?.findViewById<View>(R.id.note_indicator) ?: return
        val stringIndicator = view?.findViewById<View>(R.id.note_line_6) ?: return
        val currentNoteView = text_current_note ?: return
        val sharpButton = sharp_button ?: return
        Tutorial.start(activity ?: return) {
            addStep(noteIndicator, getString(R.string.tutorial_step_1_content))
            addStep(stringIndicator, getString(R.string.tutorial_step_2_content))
            addStep(currentNoteView, getString(R.string.tutorial_step_3_content))
            addStep(sharpButton, getString(R.string.tutorial_step_4_content))
            addStep(stringIndicator, getString(R.string.tutorial_step_5_content))
            onFinish { viewModel.onTutorialFinished() }
        }
    }

    private val Bundle.lastNotePosition: Int?
        get() = getInt(STATE_KEY_NOTE_POSITION)

    private val Bundle.lastNoteDecoration: ScoreNoteDecoration?
        get() = getSerializable(STATE_KEY_NOTE_DECORATION) as? ScoreNoteDecoration

    override fun onScorePositionChanged(position: Int) {
        viewModel.onScorePositionUpdated(position)
    }

    companion object {
        private const val STATE_KEY_NOTE_POSITION = "progress"
        private const val STATE_KEY_NOTE_DECORATION = "note_modifier"

        private const val GUITAR_UPPER_LINES = 5
        private const val GUITAR_LOWER_LINES = 3
    }
}