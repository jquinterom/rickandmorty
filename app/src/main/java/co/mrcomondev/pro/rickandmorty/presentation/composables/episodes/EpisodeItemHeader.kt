package co.mrcomondev.pro.rickandmorty.presentation.composables.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

/**
 * Created by gesoft
 */
@Composable
fun EpisodeItemHeader(title: String) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(40.dp)
      .background(
        brush = Brush.horizontalGradient(
          colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.primary
          )
        )
      ),
    contentAlignment = Alignment.CenterStart
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.onPrimary,
      modifier = Modifier.padding(start = 16.dp)
    )
  }
}