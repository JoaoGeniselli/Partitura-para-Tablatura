package com.dosei.music.scoreconverter.ui.view

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dosei.music.scoreconverter.ui.R
import kotlinx.android.synthetic.main.view_score_complete.*

class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_score_complete, container, false)
    }

    var notePosition: Int? = null
        set(value) {
            field = value
            updatePosition()
        }

    private fun updatePosition() {
        val noteListPosition = notePosition ?: return
        val guidelinePositionInDips = calculateGuidelineInDips(noteListPosition)
        val positionInPixels = convertToPixels(guidelinePositionInDips)
        guideline.setGuidelineBegin(positionInPixels)
    }

    private fun calculateGuidelineInDips(position: Int): Float {
        val multiplierInDips = 8f
        val centralizationDiff = 0.5f
        return (position + 1).toFloat() * multiplierInDips - centralizationDiff
    }

    private fun convertToPixels(positionInDips: Float): Int {
        val resources: Resources = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            positionInDips,
            resources.displayMetrics
        ).toInt()
    }
}