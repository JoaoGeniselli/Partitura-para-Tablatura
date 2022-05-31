package com.dosei.music.scoreconverter.toolbox

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class CopyToClipboard(
    val context: Context
) {

    operator fun invoke(label: String, text: CharSequence?) {
        context
            .getSystemService(Context.CLIPBOARD_SERVICE)
            ?.run { this as? ClipboardManager }
            ?.run {
                val clip = ClipData.newPlainText(label, text ?: "")
                setPrimaryClip(clip)
            }
    }
}