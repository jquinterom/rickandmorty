package co.mrcomondev.pro.rickandmorty.dataaccess.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.EpisodeDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.EpisodeApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.domain.models.Episode

/**
 * Created by gesoft
 */
class EpisodePagingSource(
  private val episodeApiService: EpisodeApiService,
  private val apiResponseMapper: ApiResponseMapper<EpisodeDto, Episode>
) : PagingSource<Int, Episode>() {

  override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
    return try {
      val pageNumber = params.key ?: 1
      val response = episodeApiService.getEpisodes(pageNumber)
      val dataResponse = apiResponseMapper.mapFromEntity(response)

      LoadResult.Page(
        data = dataResponse.results,
        prevKey = if (pageNumber > 1) pageNumber - 1 else null,
        nextKey = if (response.info.next != null) pageNumber + 1 else null
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }
}