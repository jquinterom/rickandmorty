package co.mrcomondev.pro.rickandmorty.dataaccess.api.services

import co.mrcomondev.pro.rickandmorty.dataaccess.remote.dtos.ApiResponse
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by gesoft
 */
class ApiServiceImpl @Inject constructor(private val retrofit: Retrofit) : ApiService {
  override suspend fun getCharacters(page: Int): ApiResponse {
    val service = retrofit.create(ApiService::class.java)
    return service.getCharacters(page)
  }
}