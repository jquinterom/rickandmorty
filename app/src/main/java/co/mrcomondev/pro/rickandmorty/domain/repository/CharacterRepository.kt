package co.mrcomondev.pro.rickandmorty.domain.repository

import co.mrcomondev.pro.rickandmorty.domain.models.ApiResponse
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Result

/**
 * Created by gesoft
 */
interface CharacterRepository {
  suspend fun getCharacters(page: Int): Result<ApiResponse<CharacterDomain>>
  suspend fun getCharacter(id: Int): Result<CharacterDomain>
}