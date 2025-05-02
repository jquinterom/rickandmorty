package co.mrcomondev.pro.rickandmorty.dataaccess.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.CharacterDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.ApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain

/**
 * Created by gesoft
 */
class CharacterPagingSource(
  private val apiService: ApiService,
  private val apiResponseMapper: ApiResponseMapper<CharacterDto, CharacterDomain>
) : PagingSource<Int, CharacterDomain>() {

  override fun getRefreshKey(state: PagingState<Int, CharacterDomain>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDomain> {
    return try {
      val pageNumber = params.key ?: 1
      val response = apiService.getCharacters(pageNumber)

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