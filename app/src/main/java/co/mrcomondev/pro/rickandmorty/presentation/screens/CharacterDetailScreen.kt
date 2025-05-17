package co.mrcomondev.pro.rickandmorty.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenError
import co.mrcomondev.pro.rickandmorty.presentation.composables.FullScreenLoading
import co.mrcomondev.pro.rickandmorty.presentation.composables.characters.detail.CharacterDetailContent
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.CharacterDetailViewModel
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.CharacterState
import co.mrcomondev.pro.rickandmorty.presentation.viewmodel.CommonViewModel

/**
 * Created by gesoft
 */
@Composable
fun CharacterDetailScreen(
  characterId: Int,
  viewModel: CharacterDetailViewModel = hiltViewModel(),
  commonViewModel: CommonViewModel = hiltViewModel()
) {
  LaunchedEffect(characterId) {
    viewModel.loadCharacter(characterId)
  }

  val characterState = viewModel.characterState.value
  val episodesState = viewModel.episodesState.value

  val updateFavorite = { isFavorite: Boolean ->
    commonViewModel.updateFavoriteCharacter(characterId.toString(), isFavorite)
  }

  val isFavorite = commonViewModel.isFavoriteCharacter(characterId.toString())
    .collectAsState(initial = false).value

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
          updateFavorite = updateFavorite,
          isFavorite = isFavorite
        )
      }
    }
  }
}
