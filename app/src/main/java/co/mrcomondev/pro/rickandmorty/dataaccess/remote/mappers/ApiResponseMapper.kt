package co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.ApiResponseDto
import co.mrcomondev.pro.rickandmorty.domain.models.ApiResponse
import co.mrcomondev.pro.rickandmorty.domain.models.PageInfo

/**
 * Created by gesoft
 */
class ApiResponseMapper<T : Any, R : Any>(
  private val mapper: Mapper<T, R>
) {
  fun mapFromEntity(entity: ApiResponseDto<T>): ApiResponse<R> {
    return ApiResponse(
      info = PageInfo(
        count = entity.info.count,
        pages = entity.info.pages,
        next = entity.info.next,
        prev = entity.info.prev
      ),
      results = entity.results.map { mapper.mapFromEntity(it) }
    )
  }
}