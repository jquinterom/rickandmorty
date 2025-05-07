package co.mrcomondev.pro.rickandmorty.presentation.composables.episodes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.domain.models.Episode

/**
 * Created by gesoft
 */
@Composable
fun EpisodeItemList(
  episode: Episode,
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp),
    shape = MaterialTheme.shapes.medium
  ) {
    Column {
      EpisodeItemHeader(episode.episode)

      EpisodeItemBody(episode)
    }
  }
}
