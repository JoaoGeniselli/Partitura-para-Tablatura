package com.dosei.music.scoreconverter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    MainNavigation(navController = navController)
}

@Composable
private fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "profile") {
        composable("converter") {  }
        composable("chordList") {  }
        /*...*/
    }
}