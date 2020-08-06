package com.dosei.chordsdictionary

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class LineView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {

    private var isHorizontal = true
    private val rect = Rect(0, 0, 0, 0)

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        strokeWidth = context.dpToPixels(1)
    }

    init {
        attributeSet?.let {
            updateOrientation(context, it)
        }
    }

    private fun updateOrientation(context: Context, attrs: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.LineView, 0, 0
        )
        val horizontalId = 1
        val orientationFromXml = a.getInt(R.styleable.LineView_orientation, horizontalId)
        isHorizontal = orientationFromXml == horizontalId
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            rect.set(0, 0, width, height)
            drawRect(rect, paint)
        }
    }
}