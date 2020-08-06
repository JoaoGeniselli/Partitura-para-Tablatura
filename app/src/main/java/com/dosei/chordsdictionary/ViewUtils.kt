package com.dosei.chordsdictionary

import android.content.Context
import android.util.TypedValue

fun Context.dpToPixels(dip: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dip.toFloat(),
        resources.displayMetrics
    )
}