package com.dosei.chordsdictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val positions = this.positions ?: return
        val nonePosition = "X"
        note_line_1.text = positions.chord1?.toString() ?: nonePosition
        note_line_2.text = positions.chord2?.toString() ?: nonePosition
        note_line_3.text = positions.chord3?.toString() ?: nonePosition
        note_line_4.text = positions.chord4?.toString() ?: nonePosition
        note_line_5.text = positions.chord5?.toString() ?: nonePosition
        note_line_6.text = positions.chord6?.toString() ?: nonePosition
    }
}