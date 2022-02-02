package com.dosei.music.scoreconverter.ui.view

import android.os.Bundle
import android.view.*
import androidx.annotation.IntRange
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.dosei.music.scoreconverter.ui.databinding.ViewScoreCompleteBinding
import kotlin.math.max
import kotlin.math.min

class ScoreFragment : Fragment(), View.OnTouchListener, GestureDetector.OnGestureListener {

    private lateinit var densityCalculator: DensityCalculator

    private var _binding: ViewScoreCompleteBinding? = null
    private val binding: ViewScoreCompleteBinding get() = _binding!!

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
    ): View {
        _binding = ViewScoreCompleteBinding.inflate(inflater, container, false)
        return binding.root
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
            binding.upperLine1,
            binding.upperLine2,
            binding.upperLine3,
            binding.upperLine4,
            binding.upperLine5,
            binding.upperLine6,
            binding.upperLine7,
            binding.upperLine8,
            binding.upperLine9
        )

        val allLowerLines = listOf(
            binding.lowerLine1,
            binding.lowerLine2,
            binding.lowerLine3,
            binding.lowerLine4,
            binding.lowerLine5,
            binding.lowerLine6
        )

        allUpperLines.subList(0, upperLines).forEach { it.visibility = View.VISIBLE }
        allLowerLines.subList(0, lowerLines).forEach { it.visibility = View.VISIBLE }
    }

    private fun updatePosition() {
        noteDecoration?.let {
            binding.noteDecoration.setImageResource(it.resource)
        } ?: binding.noteDecoration.setImageDrawable(null)
        val noteListPosition = notePosition ?: return
        val guidelinePositionInDips = calculateGuidelineInDips(noteListPosition)
        val positionInPixels = densityCalculator.dipsToPixels(guidelinePositionInDips)
        binding.guideline.setGuidelineBegin(positionInPixels)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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