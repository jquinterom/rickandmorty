package co.mrcomondev.pro.rickandmorty.dataaccess.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.CharacterDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.CharacterApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.CharacterMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.EpisodeMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.paging.CharacterPagingSource
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.domain.models.Result
import co.mrcomondev.pro.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by gesoft
 */

class CharacterRepositoryImpl @Inject constructor(
  private val characterApiService: CharacterApiService,
  private val characterMapper: CharacterMapper,
  private val apiResponseMapper: ApiResponseMapper<CharacterDto, CharacterDomain>,
  private val episodeMapper: EpisodeMapper
) : CharacterRepository {

  override suspend fun getCharacter(id: Int): Result<CharacterDomain> {
    return try {
      val response = characterApiService.getCharacter(id)
      Result.Success(characterMapper.mapFromEntity(response))
    } catch (e: Exception) {
      Result.Failure(e)
    }
  }

  override fun getCharactersStream(): Flow<PagingData<CharacterDomain>> {
    return Pager(
      config = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
      ),
      pagingSourceFactory = {
        CharacterPagingSource(characterApiService, apiResponseMapper)
      }
    ).flow
  }

  override suspend fun getEpisodes(episodeIds: List<Int>): List<Episode> {
    val response = characterApiService.getEpisodes(episodeIds.toString())
    return response.map { episodeMapper.mapFromEntity(it) }
  }
}
