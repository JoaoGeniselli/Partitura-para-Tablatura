package com.dosei.music.scoreconverter.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dosei.music.scoreconverter.feature.chords.dictionary.ChordsDictionaryScreen
import com.dosei.music.scoreconverter.feature.chords.transposer.TransposerScreen
import com.dosei.music.scoreconverter.feature.converter.tablature.ScoreToTablatureContent
import com.dosei.music.scoreconverter.feature.converter.tablature.ScoreToTablatureScreen
import com.dosei.music.scoreconverter.feature.home.HomeScreen
import com.dosei.music.scoreconverter.main.Feature
import kotlinx.coroutines.launch

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    MainNavigation(navController = navController)
}

@Composable
private fun MainNavigation(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedFeature by remember { mutableStateOf<Feature?>(null) }
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onSelectFeature = {
                    navController.navigate(it.route)
                    selectedFeature = it
                },
                onAboutClicked = {}
            )
        }
        composable("about") {

        }
        composable("converter_tab") {
            AppDrawer(
                drawerState = drawerState,
                currentFeature = selectedFeature,
                onSelect = {
                    navController.navigate(it.route)
                    selectedFeature = it
                }
            ) {
                ScoreToTablatureScreen(
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        }
        composable("dictionary") {
            AppDrawer(
                drawerState = drawerState,
                currentFeature = selectedFeature,
                onSelect = {
                    navController.navigate(it.route)
                    selectedFeature = it
                }
            ) {
                ChordsDictionaryScreen(
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        }
        composable("transposer") {
            AppDrawer(
                drawerState = drawerState,
                currentFeature = selectedFeature,
                onSelect = {
                    navController.navigate(it.route)
                    selectedFeature = it
                }
            ) {
                TransposerScreen(
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        }
    }
}