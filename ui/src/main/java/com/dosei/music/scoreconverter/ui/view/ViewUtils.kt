package com.dosei.music.scoreconverter.ui.view

import android.content.Context
import android.util.TypedValue

fun Context.dpToPixels(dip: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dip.toFloat(),
        resources.displayMetrics
    )
}

interface DensityCalculator {
    fun dipsToPixels(positionInDips: Float): Int
    fun pixelsToDips(pixels: Float): Int

    companion object {
        fun lightweight(context: Context): DensityCalculator = LightweightDensityCalculator(context)
    }
}

private class LightweightDensityCalculator(context: Context): DensityCalculator {

    private val density = context.resources.displayMetrics.density

    override fun dipsToPixels(positionInDips: Float) =
        positionInDips.times(density).plus(0.5f).toInt()

    override fun pixelsToDips(pixels: Float) =
        pixels.minus(0.5f).div(density).toInt()

}