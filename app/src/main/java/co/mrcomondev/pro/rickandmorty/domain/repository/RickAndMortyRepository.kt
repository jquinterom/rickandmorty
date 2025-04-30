package co.mrcomondev.pro.rickandmorty.domain.repository

import co.mrcomondev.pro.rickandmorty.dataaccess.remote.dtos.ApiResponse
import co.mrcomondev.pro.rickandmorty.dataaccess.api.services.ApiService
import javax.inject.Inject

/**
 * Created by gesoft
 */
class RickAndMortyRepository @Inject constructor(private val service: ApiService) {
  suspend fun getCharacters(page: Int): ApiResponse {
    return service.getCharacters(page)
  }
}