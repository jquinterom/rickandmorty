package co.mrcomondev.pro.rickandmorty.domain.repository

import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.domain.models.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by gesoft
 */
interface CharacterRepository {
  suspend fun getCharacter(id: Int): Result<CharacterDomain>
  fun getCharactersStream(): Flow<PagingData<CharacterDomain>>
  suspend fun getEpisodes(episodeIds: List<Int>): List<Episode>
}