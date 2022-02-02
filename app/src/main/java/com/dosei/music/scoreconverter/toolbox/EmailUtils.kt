package com.dosei.music.scoreconverter.toolbox

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent

fun sendEmail(
    activity: Activity,
    recipients: Array<String>,
    subject: String? = null,
    body: String? = null,
    chooserTitle: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "message/rfc822"
        putExtra(Intent.EXTRA_EMAIL, recipients)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    try {
        activity.startActivity(Intent.createChooser(intent, chooserTitle))
    } catch (ex: ActivityNotFoundException) {
        // Ignore
    }
}