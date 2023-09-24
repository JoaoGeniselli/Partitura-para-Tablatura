package com.dosei.music.scoreconverter.toolbox

import android.content.Context

class AssetsReader(
    private val context: Context
) {

    fun readLines(filename: String): Sequence<String> =
        context.assets.open(filename).bufferedReader().lineSequence()
}