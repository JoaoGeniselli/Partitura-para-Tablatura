package com.dosei.music.scoreconverter.ui.view

import android.os.Bundle
import android.view.*
import androidx.annotation.IntRange
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.dosei.music.scoreconverter.ui.R
import kotlinx.android.synthetic.main.view_score_complete.*
import kotlin.math.max
import kotlin.math.min

class ScoreFragment : Fragment(), View.OnTouchListener, GestureDetector.OnGestureListener {

    private lateinit var densityCalculator: DensityCalculator

    private val gestureDetector = GestureDetector(context, this)

    private val upperLines: Int
        get() = arguments?.getInt(ARG_UPPER_LINES, MAX_UPPER_LINES) ?: MAX_UPPER_LINES

    private val lowerLines: Int
        get() = arguments?.getInt(ARG_LOWER_LINES, MAX_LOWER_LINES) ?: MAX_LOWER_LINES

    private val lines = MAIN_LINES + upperLines + lowerLines

    var maxPosition: Int = lines * LINES_PLUS_SPACES

    var onPositionChangedListener: OnPositionChangedListener? = null

    var notePosition: Int? = null
        set(value) {
            field = value
            updatePosition()
        }

    var noteDecoration: ScoreNoteDecoration? = null
        set(value) {
            field = value
            updatePosition()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_score_complete, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let { densityCalculator = DensityCalculator.lightweight(it) }
        (view as? ViewGroup)?.apply {
            clipToPadding = false
            clipChildren = false
        }
        view?.setOnTouchListener(this)

        val allUpperLines = listOf(
            upper_line_1,
            upper_line_2,
            upper_line_3,
            upper_line_4,
            upper_line_5,
            upper_line_6,
            upper_line_7,
            upper_line_8,
            upper_line_9
        )

        val allLowerLines = listOf(
            lower_line_1,
            lower_line_2,
            lower_line_3,
            lower_line_4,
            lower_line_5,
            lower_line_6
        )

        allUpperLines.subList(0, upperLines).forEach { it.visibility = View.VISIBLE }
        allLowerLines.subList(0, lowerLines).forEach { it.visibility = View.VISIBLE }
    }

    private fun updatePosition() {
        noteDecoration?.let {
            note_decoration.setImageResource(it.resource)
        } ?: note_decoration.setImageDrawable(null)
        val noteListPosition = notePosition ?: return
        val guidelinePositionInDips = calculateGuidelineInDips(noteListPosition)
        val positionInPixels = densityCalculator.dipsToPixels(guidelinePositionInDips)
        guideline.setGuidelineBegin(positionInPixels)
    }

    private fun calculateGuidelineInDips(position: Int): Float {
        val multiplierInDips = 8f
        val centralizationDiff = 0.5f
        return (position + 1).toFloat() * multiplierInDips - centralizationDiff
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)

        if (event?.action == MotionEvent.ACTION_UP) {
            onPositionChangedListener?.onScorePositionChanged(notePosition ?: 0)
        }

        return true
    }

    override fun onShowPress(p0: MotionEvent?) = Unit
    override fun onSingleTapUp(p0: MotionEvent?): Boolean = false
    override fun onDown(p0: MotionEvent?): Boolean = false
    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean = false
    override fun onLongPress(p0: MotionEvent?) = Unit

    override fun onScroll(
        startEvent: MotionEvent?,
        currentEvent: MotionEvent?,
        p2: Float,
        p3: Float
    ): Boolean {
        currentEvent ?: return false
        val yInDips = densityCalculator.pixelsToDips(currentEvent.y)
        val closestPosition = yInDips / 8
        val updatedPosition = max(min(closestPosition, maxPosition), 0)
        notePosition = updatedPosition
        return true
    }

    interface OnPositionChangedListener {
        fun onScorePositionChanged(position: Int)
    }

    companion object {

        private const val ARG_UPPER_LINES = "ARG_UPPER_LINES"
        private const val ARG_LOWER_LINES = "ARG_LOWER_LINES"

        private const val MAX_UPPER_LINES = 9
        private const val MAX_LOWER_LINES = 6
        private const val MAIN_LINES = 5
        private const val LINES_PLUS_SPACES = 2

        fun newInstance(
            @IntRange(from = 0, to = MAX_UPPER_LINES.toLong()) upperLines: Int,
            @IntRange(from = 0, to = MAX_LOWER_LINES.toLong()) lowerLines: Int
        ): ScoreFragment {
            return ScoreFragment().apply {
                arguments = bundleOf(
                    ARG_UPPER_LINES to upperLines,
                    ARG_LOWER_LINES to lowerLines
                )
            }
        }
    }
}