package com.dosei.music.scoreconverter.toolbox

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri

private const val URL_MARKET = "market://details?id="
const val URL_PLAY_STORE = "http://play.google.com/store/apps/details?id="

fun goToPlayStore(
    activity: Activity,
    appPackage: String
) {
    val marketIntent = createMarketIntent(appPackage)
    try {
        activity.startActivity(marketIntent)
    } catch (e: ActivityNotFoundException) {
        val playStoreIntent = createPlayStoreIntent(appPackage)
        activity.startActivity(playStoreIntent)
    }
}

private fun createMarketIntent(appPackage: String): Intent {
    val uri: Uri = Uri.parse(URL_MARKET + appPackage)
    return Intent(Intent.ACTION_VIEW, uri).apply {
        addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
    }
}

private fun createPlayStoreIntent(appPackage: String) = Intent(
    Intent.ACTION_VIEW,
    Uri.parse(URL_PLAY_STORE + appPackage)
)
