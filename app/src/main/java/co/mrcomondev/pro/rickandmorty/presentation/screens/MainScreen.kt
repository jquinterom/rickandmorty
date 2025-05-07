package co.mrcomondev.pro.rickandmorty.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import co.mrcomondev.pro.rickandmorty.presentation.composables.navigation.AppTopBar
import co.mrcomondev.pro.rickandmorty.presentation.composables.navigation.BottomNavigationBar
import co.mrcomondev.pro.rickandmorty.presentation.navigation.NavGraph
import co.mrcomondev.pro.rickandmorty.presentation.navigation.getCurrentDestination

/**
 * Created by gesoft
 */
@Composable
fun MainScreen() {
  val navController = rememberNavController()
  val currentRoute = remember { mutableStateOf("") }

  val currentDestination = getCurrentDestination(currentRoute.value)
  val showTopBar = currentRoute.value.contains("CharacterDetail")
  val topBarTitle = if (showTopBar) "Character Details" else null

  LaunchedEffect(navController) {
    navController.currentBackStackEntryFlow.collect { entry ->
      currentRoute.value = entry.destination.route ?: ""
    }
  }

  Scaffold(
    bottomBar = {
      BottomNavigationBar(
        navController = navController,
        currentRoute = currentDestination
      )
    },
    topBar = {
      if (showTopBar && topBarTitle != null) {
        AppTopBar(
          title = topBarTitle,
          onBackPressed = { navController.popBackStack() }
        )
      }
    },
  ) { padding ->
    Box(modifier = Modifier.padding(padding)) {
      NavGraph(navController = navController)
    }
  }
}