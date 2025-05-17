package co.mrcomondev.pro.rickandmorty.presentation.composables.characters.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.EpisodesState

/**
 * Created by gesoft
 */

@Composable
fun CharacterDetailContent(
  character: CharacterDomain,
  episodesState: EpisodesState,
  updateFavorite: (Boolean) -> Unit,
  isFavorite: Boolean
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
  ) {

    CharacterHeaderSection(character, updateFavorite, isFavorite)

    CharacterInfoSection(character)

    Box(modifier = Modifier.fillMaxHeight()) {
      EpisodesSection(episodesState, character.episode.size)
    }
  }
}