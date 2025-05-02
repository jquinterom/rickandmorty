package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.mrcomondev.pro.rickandmorty.domain.models.Result
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by gesoft
 */
@HiltViewModel
class CharacterListViewModel @Inject constructor(
  private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

  private val _characterResponse = MutableLiveData<CharacterListState>(
    CharacterListState(isLoading = false)
  )
  val characterResponse: LiveData<CharacterListState> = _characterResponse

  private var currentPage = 1

  init {
    loadCharacters()
  }

  fun loadCharacters() {
    viewModelScope.launch(Dispatchers.IO) {
      _characterResponse.postValue(_characterResponse.value?.copy(isLoading = true))

      val result = getCharactersUseCase(currentPage)
      when (result) {
        is Result.Success -> {
          val currentCharacters = _characterResponse.value?.characters.orEmpty()

          _characterResponse.postValue(
            _characterResponse.value?.copy(
              characters = currentCharacters + result.data.results,
              error = null,
              isLoading = false,
              endReached = result.data.info.next == null
            )
          )

          currentPage++
        }

        is Result.Failure -> {
          _characterResponse.postValue(
            _characterResponse.value?.copy(
              error = result.exception.message ?: "Unknown error"
            )
          )
        }
      }
    }
  }
}

