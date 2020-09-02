package com.dosei.music.scoreconverter.io

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesClient(private val context: Context) {

    private val prefs: SharedPreferences
        get() = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    fun setBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false) =
        prefs.getBoolean(key, defaultValue)

    companion object {

        private const val PREFS_FILENAME = "score-converter-main"
    }
}