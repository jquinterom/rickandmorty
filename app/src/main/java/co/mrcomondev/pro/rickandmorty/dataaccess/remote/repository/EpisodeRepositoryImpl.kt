package co.mrcomondev.pro.rickandmorty.dataaccess.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.common.Constants.NETWORK_PAGE_SIZE
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.EpisodeDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.EpisodeApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.paging.EpisodePagingSource
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.domain.repository.EpisodeRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by gesoft
 */
class EpisodeRepositoryImpl @Inject constructor(
  private val episodeApi: EpisodeApiService,
  private val apiResponseMapper: ApiResponseMapper<EpisodeDto, Episode>
) : EpisodeRepository {
  override fun getEpisodesStream(): Flow<PagingData<Episode>> {
    return Pager(
      config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
      pagingSourceFactory = { EpisodePagingSource(episodeApi, apiResponseMapper) }
    ).flow
  }
}