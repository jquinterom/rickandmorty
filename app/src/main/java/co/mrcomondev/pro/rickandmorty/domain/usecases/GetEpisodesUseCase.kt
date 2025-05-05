package co.mrcomondev.pro.rickandmorty.domain.usecases

import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

/**
 * Created by gesoft
 */
class GetEpisodesUseCase @Inject constructor(
  private val repository: CharacterRepository
) {
  suspend operator fun invoke(episodeIds: List<Int>): List<Episode> {
    val result = repository.getEpisodes(episodeIds)
    return result
  }
}