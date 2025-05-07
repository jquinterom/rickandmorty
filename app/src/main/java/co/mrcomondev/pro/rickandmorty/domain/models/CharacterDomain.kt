package co.mrcomondev.pro.rickandmorty.domain.models

/**
 * Created by gesoft
 */

data class CharacterDomain(
  val id: Int,
  val name: String,
  val image: String,
  val episode: List<String>,
  val status: String,
  val species: String,
  val gender: String,
  val type: String,
  val origin: Origin,
  val locationName: String
)
