package com.dosei.chordsdictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.view_tablature_complete.*

class TablatureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_tablature_complete, container, false)
    }

    var positions: GuitarPositions? = null
        set(value) {
            field = value
            updatePositions()
        }

    private fun updatePositions() {
        positions?.run {
            updateAllChordPositions(
                note_line_1 to chord1,
                note_line_2 to chord2,
                note_line_3 to chord3,
                note_line_4 to chord4,
                note_line_5 to chord5,
                note_line_6 to chord6
            )
        }
    }

    private fun updateAllChordPositions(vararg pairs: Pair<TextView, Int?>) {
        pairs.forEach { updatePosition(it.first, it.second) }
    }

    private fun updatePosition(lineLabel: TextView, position: Int?) {
        position?.let { showPosition(lineLabel, it) } ?: hidePosition(lineLabel)
    }

    private fun showPosition(label: TextView, position: Int) {
        label.visibility = View.VISIBLE
        label.text = position.toString()
    }

    private fun hidePosition(label: TextView) {
        label.visibility = View.GONE
    }
}