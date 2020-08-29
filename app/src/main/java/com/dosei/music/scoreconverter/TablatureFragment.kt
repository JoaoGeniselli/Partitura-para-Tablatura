package com.dosei.music.scoreconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dosei.music.scoreconverter.domain.GuitarPositions
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
                note_line_1 to string1,
                note_line_2 to string2,
                note_line_3 to string3,
                note_line_4 to string4,
                note_line_5 to string5,
                note_line_6 to string6
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