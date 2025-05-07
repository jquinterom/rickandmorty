package co.mrcomondev.pro.rickandmorty.dataaccess.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.LocationDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.LocationApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.domain.models.LocationDomain

/**
 * Created by gesoft
 */
class LocationPagingSource(
  private val locationApiService: LocationApiService,
  private val apiResponseMapper: ApiResponseMapper<LocationDto, LocationDomain>
) : PagingSource<Int, LocationDomain>() {

  override fun getRefreshKey(state: PagingState<Int, LocationDomain>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationDomain> {
    return try {
      val pageNumber = params.key ?: 1
      val response = locationApiService.getLocations(pageNumber)
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