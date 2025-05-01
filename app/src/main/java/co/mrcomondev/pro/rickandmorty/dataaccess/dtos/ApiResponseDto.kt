package co.mrcomondev.pro.rickandmorty.dataaccess.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by gesoft
 */
@JsonClass(generateAdapter = true)
data class ApiResponseDto<T>(
  @Json(name = "results") val results: List<T>,
  @Json(name = "info") val info: PageInfoDto
)

@JsonClass(generateAdapter = true)
data class PageInfoDto(
  @Json(name = "count") val count: Int,
  @Json(name = "pages") val pages: Int,
  @Json(name = "next") val next: String?,
  @Json(name = "prev") val prev: String?
)