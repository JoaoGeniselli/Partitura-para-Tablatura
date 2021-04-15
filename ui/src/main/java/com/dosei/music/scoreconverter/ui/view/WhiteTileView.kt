package com.dosei.music.scoreconverter.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.dosei.music.scoreconverter.ui.R

class WhiteTileView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {

    private var lineColor = Color.BLACK
    private val rect = Rect(0, 0, 0, 0)
    private val paint: Paint

    init {
        attributeSet?.let {
            updateAttributes(context, it)
        }
        paint = Paint().apply {
            color = lineColor
            style = Paint.Style.STROKE
            strokeWidth = context.dpToPixels(1)
        }
    }

    private fun updateAttributes(context: Context, attrs: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.LineView, 0, 0
        )
        lineColor = a.getColor(R.styleable.LineView_lineColor, lineColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            rect.set(0, 0, width, height)
            drawRect(rect, paint)
        }
    }
}