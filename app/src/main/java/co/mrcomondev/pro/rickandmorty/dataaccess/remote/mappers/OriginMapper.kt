package co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.OriginDto
import co.mrcomondev.pro.rickandmorty.domain.models.Origin
import javax.inject.Inject

/**
 * Created by gesoft
 */
class OriginMapper @Inject constructor() : Mapper<OriginDto, Origin> {
  override fun mapFromEntity(entity: OriginDto): Origin {
    return Origin(
      name = entity.name
    )
  }

  override fun mapToEntity(domain: Origin): OriginDto {
    return OriginDto(
      name = domain.name
    )
  }
}