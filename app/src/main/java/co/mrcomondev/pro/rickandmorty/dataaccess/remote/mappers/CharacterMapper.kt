package co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.CharacterDto
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import javax.inject.Inject

/**
 * Created by gesoft
 */

class CharacterMapper @Inject constructor(
  private val originMapper: OriginMapper,
  private val locationMapper: LocationMapper
) : Mapper<CharacterDto, CharacterDomain> {
  override fun mapFromEntity(entity: CharacterDto): CharacterDomain {
    return CharacterDomain(
      id = entity.id,
      name = entity.name,
      image = entity.image,
      episode = entity.episode,
      status = entity.status,
      species = entity.species,
      gender = entity.gender,
      type = entity.type,
      origin = originMapper.mapFromEntity(entity.origin),
      location = locationMapper.mapFromEntity(entity.location)
    )
  }

  override fun mapToEntity(domain: CharacterDomain): CharacterDto {
    return CharacterDto(
      id = domain.id,
      name = domain.name,
      image = domain.image,
      episode = domain.episode,
      status = domain.status,
      species = domain.species,
      gender = domain.gender,
      type = domain.type,
      origin = originMapper.mapToEntity(domain.origin),
      location = locationMapper.mapToEntity(domain.location)
    )
  }
}