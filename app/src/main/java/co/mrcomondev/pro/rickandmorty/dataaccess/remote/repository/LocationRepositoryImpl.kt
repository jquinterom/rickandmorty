package co.mrcomondev.pro.rickandmorty.dataaccess.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.common.Constants.NETWORK_PAGE_SIZE
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.LocationDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.LocationApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.paging.LocationPagingSource
import co.mrcomondev.pro.rickandmorty.domain.models.LocationDomain
import co.mrcomondev.pro.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by gesoft
 */
class LocationRepositoryImpl @Inject constructor(
  private val locationApiService: LocationApiService,
  private val apiResponseMapper: ApiResponseMapper<LocationDto, LocationDomain>,
) :
  LocationRepository {
  override fun getLocationStream(): Flow<PagingData<LocationDomain>> {
    return Pager(
      config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
      pagingSourceFactory = { LocationPagingSource(locationApiService, apiResponseMapper) }
    ).flow
  }

}