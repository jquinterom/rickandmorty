package co.mrcomondev.pro.rickandmorty.presentation.composables.navigation

import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import co.mrcomondev.pro.rickandmorty.presentation.navigation.AppDestinations

/**
 * Created by gesoft
 */
@Composable
fun BottomNavigationBar(
  navController: NavController,
  currentRoute: AppDestinations?
) {
  NavigationBar {
    AppDestinations.getBottomNavItems().forEach { item ->
      NavigationBarItem(
        icon = {
          Icon(
            imageVector = item.icon,
            contentDescription = item.title
          )
        },
        label = { Text(item.title) },
        selected = currentRoute == item.destination,
        onClick = {
          try {
            navController.navigate(item.destination) {
              launchSingleTop = true
              restoreState = true
              popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
              }
            }
          } catch (e: Exception) {
            Log.e("Navigation", "Error navigating to ${item.destination}", e)
          }
        }
      )
    }
  }
}