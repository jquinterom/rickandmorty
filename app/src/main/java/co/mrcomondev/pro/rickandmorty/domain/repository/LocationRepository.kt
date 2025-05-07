package co.mrcomondev.pro.rickandmorty.domain.repository

import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.domain.models.LocationDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by gesoft
 */
interface LocationRepository {
  fun getLocationStream(): Flow<PagingData<LocationDomain>>
}