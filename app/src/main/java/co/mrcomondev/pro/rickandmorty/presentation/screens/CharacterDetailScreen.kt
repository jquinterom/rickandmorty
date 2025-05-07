package co.mrcomondev.pro.rickandmorty.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.presentation.composables.CharacterImage
import co.mrcomondev.pro.rickandmorty.presentation.composables.ErrorItem
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenError
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenLoading
import co.mrcomondev.pro.rickandmorty.presentation.composables.LoadingItem
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.CharacterDetailViewModel
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.CharacterState
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.EpisodesState

/**
 * Created by gesoft
 */
@Composable
fun CharacterDetailScreen(
  characterId: Int,
  viewModel: CharacterDetailViewModel = hiltViewModel(),
) {
  LaunchedEffect(characterId) {
    viewModel.loadCharacter(characterId)
  }

  val characterState = viewModel.characterState.value
  val episodesState = viewModel.episodesState.value

  Box {
    when (characterState) {
      is CharacterState.Loading -> {
        FullScreenLoading()
      }

      is CharacterState.Error -> {
        FullScreenError(
          message = characterState.message,
          onRetry = { viewModel.loadCharacter(characterId) }
        )
      }

      is CharacterState.Success -> {
        CharacterDetailContent(
          character = characterState.character,
          episodesState = episodesState,
        )
      }
    }
  }
}

@Composable
private fun CharacterDetailContent(
  character: CharacterDomain,
  episodesState: EpisodesState,
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
  ) {

    CharacterHeaderSection(character)

    CharacterInfoSection(character)

    Box(modifier = Modifier.fillMaxHeight()) {
      EpisodesSection(episodesState, character.episode.size)
    }
  }
}

@Composable
private fun CharacterHeaderSection(character: CharacterDomain) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxWidth()
  ) {
    CharacterImage(modifier = Modifier.size(200.dp), image = character.image, name = character.name)

    Spacer(modifier = Modifier.height(16.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(
        text = character.name,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(4.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        StatusIndicator(status = character.status)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "${character.status} - ${character.species}",
          style = MaterialTheme.typography.titleMedium
        )
      }
    }
  }
}


@Composable
private fun CharacterInfoSection(character: CharacterDomain) {
  Card(
    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      InfoRow(label = "Género", value = character.gender)
      HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
      InfoRow(label = "Tipo", value = character.type.ifEmpty { "Desconocido" })
      HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
      )
      InfoRow(label = "Origen", value = character.origin.name)
      HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
      InfoRow(label = "Ubicación", value = character.location.name)
    }
  }
}

@Composable
private fun EpisodesSection(episodesState: EpisodesState, totalEpisodes: Int) {
  Card(
    modifier = Modifier
      .padding(16.dp)
      .fillMaxWidth()
      .height(200.dp)
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

@Composable
private fun EpisodeItem(episode: Episode) {
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

@Composable
private fun InfoRow(label: String, value: String) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    )
    Text(
      text = value,
      style = MaterialTheme.typography.bodyLarge,
      textAlign = TextAlign.End
    )
  }
}

@Composable
private fun StatusIndicator(status: String) {
  val color = when (status.lowercase()) {
    "alive" -> Color(0xFF55CC44)
    "dead" -> Color(0xFFD63D2E)
    else -> Color(0xFF9E9E9E)
  }

  Box(
    modifier = Modifier
      .size(10.dp)
      .clip(CircleShape)
      .background(color)
  )
}