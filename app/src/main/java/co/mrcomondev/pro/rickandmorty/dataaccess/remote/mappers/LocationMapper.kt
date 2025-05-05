package co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.LocationDto
import co.mrcomondev.pro.rickandmorty.domain.models.LocationDomain
import javax.inject.Inject

/**
 * Created by gesoft
 */
class LocationMapper @Inject constructor() : Mapper<LocationDto, LocationDomain> {
  override fun mapFromEntity(entity: LocationDto): LocationDomain {
    return LocationDomain(
      name = entity.name,
    )
  }

  override fun mapToEntity(domain: LocationDomain): LocationDto {
    return LocationDto(
      name = domain.name,
    )
  }
}