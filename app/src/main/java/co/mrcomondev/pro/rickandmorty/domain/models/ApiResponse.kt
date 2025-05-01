package co.mrcomondev.pro.rickandmorty.domain.models


/**
 * Created by gesoft
 */
data class ApiResponse<T>(
  val results: List<T>,
  val info: PageInfo
)

