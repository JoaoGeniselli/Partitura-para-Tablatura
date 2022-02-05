package com.dosei.music.scoreconverter.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import org.junit.Rule
import org.junit.Test

class ComposableConverterKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testScroll() {
        composeTestRule.setContent {
            ComposableConverter(Modifier.fillMaxSize())
        }

        composeTestRule.onNodeWithTag("score").performTouchInput {
            swipeDown(
                startY = 0f,
                endY = 150f,
                durationMillis = 3000
            )
        }

        Thread.sleep(3000)

    }
}