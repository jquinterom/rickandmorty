package co.mrcomondev.pro.rickandmorty.dataaccess.api.services

import co.mrcomondev.pro.rickandmorty.dataaccess.remote.dtos.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gesoft
 */
interface ApiService {
  @GET("character")
  suspend fun getCharacters(
    @Query("page") page: Int
  ): ApiResponse
}