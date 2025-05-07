package co.mrcomondev.pro.rickandmorty.domain.models

/**
 * Created by gesoft
 */
data class LocationDomain(
  val id: Int,
  val name: String,
  val type: String,
  val dimension: String,
  val residents: List<String>
)
