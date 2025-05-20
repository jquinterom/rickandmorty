package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.domain.models.Result
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetCharacterUseCase
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by gesoft
 */
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
  private val getCharacterUseCase: GetCharacterUseCase,
  private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModel() {

  private val _characterState = mutableStateOf<CharacterState>(CharacterState.Loading)
  val characterState: MutableState<CharacterState> = _characterState

  private val _episodesState = mutableStateOf<EpisodesState>(EpisodesState.Loading)
  val episodesState: MutableState<EpisodesState> = _episodesState

  fun loadCharacter(characterId: Int) {
    viewModelScope.launch {
      _characterState.value = CharacterState.Loading
      _episodesState.value  = EpisodesState.Loading

      when (val result = getCharacterUseCase(characterId)) {
        is Result.Success -> {
          _characterState.value = CharacterState.Success(result.data)
          loadEpisodes(result.data.episode)
        }

        is Result.Failure -> {
          _characterState.value = CharacterState.Error(
            result.exception.message ?: "Error to load character"
          )
          _episodesState.value  = EpisodesState.Error("Character not found, episodes not loaded")
        }
      }
    }
  }

  private suspend fun loadEpisodes(episodeUrls: List<String>) {
    try {
      val episodeIds = episodeUrls.map { url ->
        url.substringAfterLast("/").toInt()
      }

      val episodes = getEpisodesUseCase(episodeIds)
      _episodesState.value = EpisodesState.Success(episodes)
    } catch (e: Exception) {
      _episodesState.value = EpisodesState.Error(
        e.message ?: "Error to load episodes"
      )
    }
  }

}

sealed class CharacterState {
  object Loading : CharacterState()
  data class Success(val character: CharacterDomain) : CharacterState()
  data class Error(val message: String) : CharacterState()
}

sealed class EpisodesState {
  object Loading : EpisodesState()
  data class Success(val episodes: List<Episode>) : EpisodesState()
  data class Error(val message: String) : EpisodesState()
}