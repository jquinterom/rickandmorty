package co.mrcomondev.pro.rickandmorty.presentation.composables.characters.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.Episode

/**
 * Created by gesoft
 */
@Composable
fun EpisodeItem(episode: Episode) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = episode.name,
        style = MaterialTheme.typography.titleMedium
      )
      Text(
        text = "${episode.episode} - ${episode.airDate}",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
      )
    }
  }
}
