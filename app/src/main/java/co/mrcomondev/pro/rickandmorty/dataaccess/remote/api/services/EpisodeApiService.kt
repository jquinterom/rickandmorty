package co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.ApiResponseDto
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gesoft
 */
interface EpisodeApiService {
  @GET("episode")
  suspend fun getEpisodes(
    @Query("page") page: Int
  ): ApiResponseDto<EpisodeDto>
}
