package co.mrcomondev.pro.rickandmorty.dataaccess.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by gesoft
 */
@JsonClass(generateAdapter = true)
data class CharacterDto(
  @Json(name = "id") val id: Int,
  @Json(name = "name") val name: String,
  @Json(name = "image") val image: String,
  @Json(name = "episode") val episode: List<String>,
  @Json(name = "status") val status: String,
  @Json(name = "species") val species: String,
  @Json(name = "gender") val gender: String,
  @Json(name = "type") val type: String,
  @Json(name = "origin") val origin: OriginDto,
  @Json(name = "location") val location: LocationDto
)