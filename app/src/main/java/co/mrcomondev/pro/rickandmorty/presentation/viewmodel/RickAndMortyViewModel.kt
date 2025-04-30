package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.models.ApiResponse
import co.mrcomondev.pro.rickandmorty.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by gesoft
 */
@HiltViewModel
class RickAndMortyViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
  ViewModel() {

  private val _characterResponse = MutableLiveData<ApiResponse>()
  val hitResponse: MutableLiveData<ApiResponse> = _characterResponse


  init {
    viewModelScope.launch(Dispatchers.IO) {
      val resultServer = repository.getCharacters(1)
      _characterResponse.postValue(resultServer)
    }
  }
}