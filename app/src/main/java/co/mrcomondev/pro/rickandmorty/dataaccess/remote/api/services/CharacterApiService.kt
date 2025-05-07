package co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.ApiResponseDto
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.CharacterDto
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by gesoft
 */
interface CharacterApiService {
  @GET("character")
  suspend fun getCharacters(
    @Query("page") page: Int
  ): ApiResponseDto<CharacterDto>

  @GET("character/{id}")
  suspend fun getCharacter(
    @Path("id") id: Int
  ): CharacterDto

  @GET("episode/{ids}")
  suspend fun getEpisodes(
    @Path("ids") episodeIds: String
  ): List<EpisodeDto>
}

