package co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.CharacterDto
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import javax.inject.Inject

/**
 * Created by gesoft
 */

class CharacterMapper @Inject constructor() : Mapper<CharacterDto, CharacterDomain> {
  override fun mapFromEntity(entity: CharacterDto): CharacterDomain {
    return CharacterDomain(
      id = entity.id,
      name = entity.name,
      image = entity.image
    )
  }

  override fun mapToEntity(domain: CharacterDomain): CharacterDto {
    return CharacterDto(
      id = domain.id,
      name = domain.name,
      image = domain.image
    )
  }
}