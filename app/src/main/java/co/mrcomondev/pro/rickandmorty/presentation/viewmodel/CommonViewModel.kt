package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.mrcomondev.pro.rickandmorty.dataaccess.local.CharactersDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by gesoft
 */
@HiltViewModel
class CommonViewModel @Inject constructor(
  private val charactersDataStore: CharactersDataStore
) : ViewModel() {

  val favoriteCharacterIdsFlow = charactersDataStore.favoriteCharactersFlow

  private fun addFavoriteCharacter(characterId: String) {
    viewModelScope.launch {
      val current = favoriteCharacterIdsFlow.first()
      charactersDataStore.updateFavoriteCharacters(current + characterId)
    }
  }

  private fun removeFavoriteCharacter(characterId: String) {
    viewModelScope.launch {
      val current = favoriteCharacterIdsFlow.first()
      charactersDataStore.updateFavoriteCharacters(current - characterId)
    }
  }

  fun isFavoriteCharacter(characterId: String): Flow<Boolean> {
    return favoriteCharacterIdsFlow.map { favoriteIds ->
      characterId in favoriteIds
    }
  }

  fun updateFavoriteCharacter(characterId: String, isFavorite: Boolean) {
    if (isFavorite) {
      removeFavoriteCharacter(characterId)
    } else {
      addFavoriteCharacter(characterId)
    }
  }
}