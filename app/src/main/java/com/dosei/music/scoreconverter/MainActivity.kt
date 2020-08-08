package com.dosei.music.scoreconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var scoreConverterFragment: ScoreConverterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initConverter()
    }

    private fun initConverter() {
        scoreConverterFragment = ScoreConverterFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, scoreConverterFragment, "ScoreConverter")
            .commit()
    }
}
