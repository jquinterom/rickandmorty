package co.mrcomondev.pro.rickandmorty.dataaccess.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by gesoft
 */
@JsonClass(generateAdapter = true)
data class EpisodeDto(
  @Json(name = "id") val id: Int,
  @Json(name = "name") val name: String,
  @Json(name = "air_date") val airDate: String,
  @Json(name = "episode") val episode: String,
  @Json(name = "characters") val characters: List<String>,
  @Json(name = "url") val url: String,
  @Json(name = "created") val created: String
)