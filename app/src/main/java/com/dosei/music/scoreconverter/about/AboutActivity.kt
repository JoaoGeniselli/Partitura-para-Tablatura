package com.dosei.music.scoreconverter.about

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.dosei.music.scoreconverter.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        text_content?.movementMethod = LinkMovementMethod.getInstance()
        getVersionName()?.let {
            text_version.text = getString(R.string.version_name_label, it)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getVersionName(): String? = try {
        val info = packageManager.getPackageInfo(packageName, 0)
        info.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}