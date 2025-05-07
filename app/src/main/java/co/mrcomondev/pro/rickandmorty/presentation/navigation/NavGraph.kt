package co.mrcomondev.pro.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
    startDestination = AppDestinations.Characters,
  ) {
    composable<AppDestinations.Characters> {
      CharacterListScreen { characterId ->
        navController.navigate(AppDestinations.CharacterDetail(characterId))
      }
    }

    composable<AppDestinations.Locations> {
      LocationListScreen(
        onLocationClick = { locationId ->
          navController.navigate(AppDestinations.LocationDetail(locationId))
        })
    }

    composable<AppDestinations.Episodes> {
      EpisodeListScreen(
        onEpisodeClick = { episodeId ->
          navController.navigate(AppDestinations.EpisodeDetail(episodeId))
        })
    }

    composable<AppDestinations.CharacterDetail> { backStackEntry ->
      val detail = backStackEntry.toRoute<AppDestinations.CharacterDetail>()
      CharacterDetailScreen(detail.characterId)
    }
  }
}