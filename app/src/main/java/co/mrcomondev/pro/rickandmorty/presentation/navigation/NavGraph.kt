package co.mrcomondev.pro.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.mrcomondev.pro.rickandmorty.presentation.screens.CharacterDetailScreen
import co.mrcomondev.pro.rickandmorty.presentation.screens.CharacterListScreen
import co.mrcomondev.pro.rickandmorty.presentation.screens.EpisodeListScreen
import co.mrcomondev.pro.rickandmorty.presentation.screens.LocationListScreen

/**
 * Created by gesoft
 */
@Composable
fun NavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = BottomNavItem.Characters.route,
  ) {
    composable(BottomNavItem.Characters.route) {
      CharacterListScreen { characterId ->
        navController.navigate("character_detail/$characterId")
      }
    }

    composable(BottomNavItem.Locations.route) {
      LocationListScreen(
        onLocationClick = { locationId ->
          navController.navigate("location_detail/$locationId")
        })
    }

    composable(BottomNavItem.Episodes.route) {
      EpisodeListScreen(
        onEpisodeClick = { episodeId ->
          navController.navigate("episode_detail/$episodeId")
        })
    }

    composable(
      route = BottomNavItem.CharacterDetail.route + "/{characterId}",
      arguments = listOf(navArgument("characterId") { type = NavType.IntType })
    ) { backStackEntry ->
      val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
      CharacterDetailScreen(
        characterId = characterId
      )
    }
  }
}