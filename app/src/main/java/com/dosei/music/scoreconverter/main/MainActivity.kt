package com.dosei.music.scoreconverter.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.about.AboutActivity
import com.dosei.music.scoreconverter.converter.ScoreConverterFragment
import com.dosei.music.scoreconverter.toolbox.URL_PLAY_STORE
import com.dosei.music.scoreconverter.toolbox.goToPlayStore
import com.dosei.music.scoreconverter.toolbox.sendEmail
import com.dosei.music.scoreconverter.toolbox.shareText

class MainActivity : AppCompatActivity() {

    private var scoreConverterFragment: ScoreConverterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adjustActionBar()
        savedInstanceState?.let {
            scoreConverterFragment = supportFragmentManager.findFragmentByTag(
                SCORE_CONVERTER_TAG
            ) as? ScoreConverterFragment
        } ?: initConverter()
    }

    private fun adjustActionBar() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }
    }

    private fun initConverter() {
        val fragment = ScoreConverterFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_container, fragment,
                SCORE_CONVERTER_TAG
            )
            .commit()
        scoreConverterFragment = fragment
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
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
