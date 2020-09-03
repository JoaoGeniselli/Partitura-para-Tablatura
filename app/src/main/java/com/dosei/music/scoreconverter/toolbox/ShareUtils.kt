package com.dosei.music.scoreconverter.toolbox

import android.app.Activity
import android.content.Intent

fun shareText(
    activity: Activity,
    content: String,
    chooserTitle: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, content)
    }
    val chooser = Intent.createChooser(intent, chooserTitle)
    activity.startActivity(chooser)
}