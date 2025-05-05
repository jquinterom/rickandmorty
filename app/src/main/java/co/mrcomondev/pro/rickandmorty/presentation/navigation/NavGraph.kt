package co.mrcomondev.pro.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.mrcomondev.pro.rickandmorty.presentation.screens.CharacterDetailScreen
import co.mrcomondev.pro.rickandmorty.presentation.screens.CharacterListScreen

/**
 * Created by gesoft
 */
@Composable
fun NavGraph(modifier: Modifier = Modifier) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = "character_list",
  ) {
    composable("character_list") {
      CharacterListScreen(modifier = modifier) { characterId ->
        navController.navigate("character_detail/$characterId")
      }
    }

    composable(
      route = "character_detail/{characterId}",
      arguments = listOf(navArgument("characterId") { type = NavType.IntType })
    ) { backStackEntry ->
      val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
      CharacterDetailScreen(characterId = characterId, onBackPressed = { navController.popBackStack() })
    }
  }
}