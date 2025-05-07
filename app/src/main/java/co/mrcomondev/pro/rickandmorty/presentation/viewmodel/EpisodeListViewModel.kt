package co.mrcomondev.pro.rickandmorty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetEpisodesStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

/**
 * Created by gesoft
 */
@HiltViewModel
class EpisodeListViewModel @Inject constructor(
  getEpisodesStream: GetEpisodesStreamUseCase
) : ViewModel() {
  val episodesPagingFlow = getEpisodesStream()
    .cachedIn(viewModelScope)
}

