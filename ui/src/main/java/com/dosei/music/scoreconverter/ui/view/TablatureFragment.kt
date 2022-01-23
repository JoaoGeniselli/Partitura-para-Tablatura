package com.dosei.music.scoreconverter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dosei.music.scoreconverter.ui.databinding.ViewTablatureCompleteBinding

class TablatureFragment : Fragment() {

    private var _binding: ViewTablatureCompleteBinding? = null
    private val binding: ViewTablatureCompleteBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewTablatureCompleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    var positions: GuitarPositions? = null
        set(value) {
            field = value
            updatePositions()
        }

    private fun updatePositions() {
        positions?.run {
            updateAllChordPositions(
                binding.noteLine1 to string1,
                binding.noteLine2 to string2,
                binding.noteLine3 to string3,
                binding.noteLine4 to string4,
                binding.noteLine5 to string5,
                binding.noteLine6 to string6
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