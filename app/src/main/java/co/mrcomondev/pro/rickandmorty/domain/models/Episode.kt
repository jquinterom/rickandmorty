package co.mrcomondev.pro.rickandmorty.domain.models

/**
 * Created by gesoft
 */
data class Episode(
  val id: Int,
  val name: String,
  val airDate: String,
  val episode: String,
  val characters: List<String>,
  val url: String,
  val created: String
)