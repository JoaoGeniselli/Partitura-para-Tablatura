package com.dosei.music.scoreconverter.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.navigation.Navigation
import com.dosei.music.scoreconverter.toolbox.URL_PLAY_STORE
import com.dosei.music.scoreconverter.toolbox.shareText
import com.dosei.music.scoreconverter.ui.theme.AppTheme
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
        MobileAds.initialize(this) {}
    }

    private fun shareApp() {
        val appName = getString(R.string.app_name)
        val appPlayStorePath = URL_PLAY_STORE + applicationContext.packageName
        shareText(
            activity = this,
            content = getString(
                R.string.share_app_content,
                appName,
                appPlayStorePath
            ),
            chooserTitle = getString(R.string.share_chooser_title)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    AppTheme {
        Surface {
            Navigation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    MainContent()
}
