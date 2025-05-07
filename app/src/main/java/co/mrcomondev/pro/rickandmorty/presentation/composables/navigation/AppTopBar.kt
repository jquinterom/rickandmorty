package co.mrcomondev.pro.rickandmorty.presentation.composables.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

/**
 * Created by gesoft
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
  title: String,
  onBackPressed: (() -> Unit)? = null
) {
  TopAppBar(
    title = { Text(title) },
    navigationIcon = {
      if (onBackPressed != null) {
        IconButton(onClick = onBackPressed) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Go back"
          )
        }
      }
    }
  )
}