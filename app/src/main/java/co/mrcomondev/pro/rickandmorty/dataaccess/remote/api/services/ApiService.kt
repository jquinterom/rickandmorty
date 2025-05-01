package co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.ApiResponseDto
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gesoft
 */
interface ApiService {
  @GET("character")
  suspend fun getCharacters(
    @Query("page") page: Int
  ): ApiResponseDto<CharacterDto>
}