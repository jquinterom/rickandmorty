package co.mrcomondev.pro.rickandmorty.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by gesoft
 */
sealed class BottomNavItem(
  val route: String,
  val title: String,
  val icon: ImageVector
) {
  object Characters : BottomNavItem(
    route = "characters",
    title = "Characters",
    icon = Icons.Default.Person
  )

  object Locations : BottomNavItem(
    route = "locations",
    title = "Locations",
    icon = Icons.Default.LocationOn
  )

  object Episodes : BottomNavItem(
    route = "episodes",
    title = "Episodes",
    icon = Icons.Default.PlayArrow
  )

  object CharacterDetail : BottomNavItem(
    route = "character_detail",
    title = "Character Detail",
    icon = Icons.Default.Menu
  )

  companion object {
    fun getItems(): List<BottomNavItem> = listOf(Characters, Locations, Episodes)
  }
}
