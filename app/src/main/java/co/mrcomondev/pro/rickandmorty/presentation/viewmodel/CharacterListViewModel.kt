package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetCharactersStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by gesoft
 */
@HiltViewModel
class CharacterListViewModel @Inject constructor(
  getCharactersStream: GetCharactersStreamUseCase
) : ViewModel() {
  val charactersPagingFlow: Flow<PagingData<CharacterDomain>> = getCharactersStream()
    .cachedIn(viewModelScope)
}

