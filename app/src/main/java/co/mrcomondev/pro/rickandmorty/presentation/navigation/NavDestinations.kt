package co.mrcomondev.pro.rickandmorty.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

/**
 * Created by gesoft
 */

data class BottomNavItem(
  val destination: AppDestinations,
  val title: String,
  val icon: ImageVector
)

@Serializable
sealed class AppDestinations() {
  @Serializable
  object Characters : AppDestinations()

  @Serializable
  object Locations : AppDestinations()

  @Serializable
  object Episodes : AppDestinations()

  @Serializable
  data class CharacterDetail(val characterId: Int) : AppDestinations()

  @Serializable
  data class LocationDetail(val locationId: Int) : AppDestinations()

  @Serializable
  data class EpisodeDetail(val episodeId: Int) : AppDestinations()

  companion object {
    fun getBottomNavItems(): List<BottomNavItem> = listOf(
      BottomNavItem(
        destination = Characters,
        title = "Characters",
        icon = Icons.Default.Person
      ),
      BottomNavItem(
        destination = Locations,
        title = "Locations",
        icon = Icons.Default.LocationOn
      ),
      BottomNavItem(
        destination = Episodes,
        title = "Episodes",
        icon = Icons.Default.PlayArrow
      )
    )
  }
}

fun getCurrentDestination(currentRoute: String?): AppDestinations? {
  return when {
    currentRoute?.contains("AppDestinations.Characters") == true -> AppDestinations.Characters
    currentRoute?.contains("AppDestinations.Locations") == true -> AppDestinations.Locations
    currentRoute?.contains("AppDestinations.Episodes") == true -> AppDestinations.Episodes
    currentRoute?.contains("CharacterDetail") == true -> AppDestinations.Characters
    currentRoute?.contains("LocationDetail") == true -> AppDestinations.Locations
    currentRoute?.contains("EpisodeDetail") == true -> AppDestinations.Episodes
    else -> null
  }
}

