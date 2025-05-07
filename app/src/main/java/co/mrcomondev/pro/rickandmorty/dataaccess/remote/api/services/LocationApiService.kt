package co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.ApiResponseDto
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.LocationDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gesoft
 */
interface LocationApiService {
  @GET("location")
  suspend fun getLocations(
    @Query("page") page: Int
  ): ApiResponseDto<LocationDto>
}
