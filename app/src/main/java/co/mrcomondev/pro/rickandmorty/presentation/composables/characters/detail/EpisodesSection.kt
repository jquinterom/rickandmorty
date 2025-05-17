package co.mrcomondev.pro.rickandmorty.presentation.composables.characters.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.mrcomondev.pro.rickandmorty.presentation.composables.ErrorItem
import co.mrcomondev.pro.rickandmorty.presentation.composables.LoadingItem
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.EpisodesState

/**
 * Created by gesoft
 */

@Composable
fun EpisodesSection(episodesState: EpisodesState, totalEpisodes: Int) {
  Card(
    modifier = Modifier
      .padding(16.dp)
      .fillMaxWidth()
      .height(180.dp)
  ) {
    Column {
      Text(
        text = "Episodes ($totalEpisodes)",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(16.dp)
      )

      when (episodesState) {
        is EpisodesState.Loading -> {
          LoadingItem()
        }

        is EpisodesState.Error -> {
          ErrorItem(
            message = episodesState.message,
            modifier = Modifier.padding(16.dp),
          )
        }

        is EpisodesState.Success -> {
          LazyColumn {
            items(episodesState.episodes) { episode ->
              EpisodeItem(episode = episode)
              if (episode != episodesState.episodes.last()) {
                HorizontalDivider()
              }
            }
          }
        }
      }
    }
  }
}