package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain

/**
 * Created by gesoft
 */
data class CharacterListState(
  val characters: List<CharacterDomain> = emptyList(),
  val isLoading: Boolean = false,
  val error: String? = null,
  val endReached: Boolean = false
)