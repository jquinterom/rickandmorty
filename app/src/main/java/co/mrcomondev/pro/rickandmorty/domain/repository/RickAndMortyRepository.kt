package co.mrcomondev.pro.rickandmorty.domain.repository

import co.mrcomondev.pro.rickandmorty.dataaccess.remote.models.ApiResponse
import co.mrcomondev.pro.rickandmorty.dataaccess.services.ApiService
import javax.inject.Inject

/**
 * Created by gesoft
 */
class RickAndMortyRepository @Inject constructor(private val service: ApiService) {
  suspend fun getCharacters(page: Int): ApiResponse {
    return service.getCharacters(page)
  }
}