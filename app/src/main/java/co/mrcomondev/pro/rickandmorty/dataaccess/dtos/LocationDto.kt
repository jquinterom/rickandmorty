package co.mrcomondev.pro.rickandmorty.dataaccess.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by gesoft
 */
@JsonClass(generateAdapter = true)
data class LocationDto(
  @Json(name = "id") val id: Int,
  @Json(name = "name") val name: String,
  @Json(name = "type") val type: String,
  @Json(name = "dimension") val dimension: String,
  @Json(name = "residents") val residents: List<String>
)