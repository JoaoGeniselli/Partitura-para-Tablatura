package com.dosei.music.scoreconverter.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.about.AboutActivity
import com.dosei.music.scoreconverter.chords.dictionary.ChordsDictionary
import com.dosei.music.scoreconverter.toolbox.URL_PLAY_STORE
import com.dosei.music.scoreconverter.toolbox.goToPlayStore
import com.dosei.music.scoreconverter.toolbox.sendEmail
import com.dosei.music.scoreconverter.toolbox.shareText
import com.dosei.music.scoreconverter.transposer.TransposerLoader
import com.dosei.music.scoreconverter.ui.theme.AppTheme
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
        MobileAds.initialize(this) {}
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.rate_the_app -> rateThisApp()
            R.id.share -> shareApp()
            R.id.about -> redirectToAboutScreen()
            R.id.contact_us -> redirectToEmailContact()
            else -> return false
        }
        return true
    }

    private fun rateThisApp() {
        goToPlayStore(
            activity = this,
            appPackage = applicationContext.packageName
        )
    }

    private fun redirectToAboutScreen() {
        startActivity(
            Intent(this, AboutActivity::class.java)
        )
    }

    private fun redirectToEmailContact() {
        val contactEmail = getString(R.string.saturn_dev_email)
        val subject = getString(R.string.app_name)
        val chooserTitle = getString(R.string.contact_chooser_title)
        sendEmail(
            activity = this,
            recipients = arrayOf(contactEmail),
            subject = subject,
            chooserTitle = chooserTitle
        )
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

    companion object {
        private const val SCORE_CONVERTER_TAG = "ScoreConverter"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val selectedFeature = remember { mutableStateOf(Feature.ScoreToTablature) }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val features = Feature.values().toList()

    AppTheme {
        Surface {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        Spacer(Modifier.height(12.dp))
                        features.forEach { item ->
                            NavigationDrawerItem(
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                label = { Text(stringResource(item.nameRes)) },
                                selected = item == selectedFeature.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedFeature.value = item
                                },
                            )
                        }
                    }
                }
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(
                            onClickMenu = { scope.launch { drawerState.open() } }
                        )
                    },
                ) {
                    val modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                    when (selectedFeature.value) {
                        Feature.ScoreToTablature -> ScoreToTablature(modifier = modifier)
                        Feature.ChordsDictionary -> ChordsDictionary(modifier = modifier)
                        Feature.Transposer -> TransposerLoader(modifier = modifier)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onClickMenu: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable(onClick = onClickMenu),
                contentDescription = stringResource(id = R.string.menu)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    MainContent()
}
