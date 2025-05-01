package co.mrcomondev.pro.rickandmorty.domain.models

/**
* Created by gesoft
*/
sealed class Result<out T> {
  data class Success<out T>(val data: T) : Result<T>()
  data class Failure(val exception: Exception) : Result<Nothing>()
}