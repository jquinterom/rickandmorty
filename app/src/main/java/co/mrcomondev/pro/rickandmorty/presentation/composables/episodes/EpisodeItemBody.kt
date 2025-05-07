package co.mrcomondev.pro.rickandmorty.presentation.composables.episodes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.Episode

/**
 * Created by gesoft
 */
@Composable
fun EpisodeItemBody(episode: Episode) {
  Column(
    modifier = Modifier.padding(16.dp)
  ) {
    Text(
      text = episode.name,
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier.padding(bottom = 4.dp)
    )

    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        Text(
          text = "Season",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
          text = episode.episode.substringAfter("S").substringBefore("E"),
          style = MaterialTheme.typography.bodyMedium
        )
      }

      Column {
        Text(
          text = "Episode",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
          text = episode.episode.substringAfter("E"),
          style = MaterialTheme.typography.bodyMedium
        )
      }

      Column {
        Text(
          text = "Date",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
          text = episode.airDate,
          style = MaterialTheme.typography.bodyMedium
        )
      }
    }
  }
}