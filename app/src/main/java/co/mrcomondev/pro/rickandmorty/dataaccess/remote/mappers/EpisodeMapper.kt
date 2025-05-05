package co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.EpisodeDto
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import javax.inject.Inject

/**
 * Created by gesoft
 */
class EpisodeMapper @Inject constructor() : Mapper<EpisodeDto, Episode> {
  override fun mapFromEntity(entity: EpisodeDto): Episode {
    return Episode(
      id = entity.id,
      name = entity.name,
      airDate = entity.airDate,
      episode = entity.episode,
      characters = entity.characters,
      url = entity.url,
      created = entity.created
    )
  }

  override fun mapToEntity(domain: Episode): EpisodeDto {
    return EpisodeDto(
      id = domain.id,
      name = domain.name,
      airDate = domain.airDate,
      episode = domain.episode,
      characters = domain.characters,
      url = domain.url,
      created = domain.created
    )
  }
}