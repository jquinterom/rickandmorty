package co.mrcomondev.pro.rickandmorty.domain.usecases

import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.domain.models.LocationDomain
import co.mrcomondev.pro.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by gesoft
 */
class GetLocationsStreamUseCase @Inject constructor(
  private val repository: LocationRepository
) {
  operator fun invoke(): Flow<PagingData<LocationDomain>> {
    return repository.getLocationStream()
  }
}