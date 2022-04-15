package com.dosei.music.scoreconverter.about

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.dosei.music.scoreconverter.R
import com.dosei.music.scoreconverter.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textContent.movementMethod = LinkMovementMethod.getInstance()
        getVersionName()?.let {
            binding.textVersion.text = getString(R.string.version_name_label, it)
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