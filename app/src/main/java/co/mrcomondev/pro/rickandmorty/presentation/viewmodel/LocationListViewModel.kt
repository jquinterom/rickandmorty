package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetLocationsStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by gesoft
 */
@HiltViewModel
class LocationListViewModel @Inject constructor(
  getLocationsStream: GetLocationsStreamUseCase
) : ViewModel() {
  val locationsPagingFlow = getLocationsStream()
    .cachedIn(viewModelScope)
}

