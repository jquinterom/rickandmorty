package co.mrcomondev.pro.rickandmorty.domain.usecases

import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by gesoft
 */
class GetEpisodesStreamUseCase @Inject constructor(
  private val repository: EpisodeRepository
) {
  operator fun invoke(): Flow<PagingData<Episode>> {
    return repository.getEpisodesStream()
  }
}