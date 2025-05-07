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
      id = entity.id,
      name = entity.name,
      type = entity.type,
      dimension = entity.dimension,
      residents = entity.residents,
    )
  }

  override fun mapToEntity(domain: LocationDomain): LocationDto {
    return LocationDto(
      id = domain.id,
      name = domain.name,
      type = domain.type,
      dimension = domain.dimension,
      residents = domain.residents,
    )
  }
}