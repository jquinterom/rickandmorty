package co.mrcomondev.pro.rickandmorty.presentation.composables.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by gesoft
 */
@Composable
fun LocationCardIcon(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .size(48.dp)
      .background(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = CircleShape
      ),
    contentAlignment = Alignment.Center
  ) {
    Icon(
      imageVector = Icons.Default.LocationOn,
      contentDescription = "Location",
      tint = MaterialTheme.colorScheme.onPrimaryContainer,
      modifier = Modifier.size(24.dp)
    )
  }
}
