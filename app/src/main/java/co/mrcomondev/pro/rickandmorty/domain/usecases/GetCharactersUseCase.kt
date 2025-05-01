package co.mrcomondev.pro.rickandmorty.domain.usecases

import co.mrcomondev.pro.rickandmorty.domain.models.ApiResponse
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Result
import co.mrcomondev.pro.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

/**
 * Created by gesoft
 */
class GetCharactersUseCase @Inject constructor(
  private val repository: CharacterRepository
) {
  suspend operator fun invoke(page: Int): Result<ApiResponse<CharacterDomain>> {
    return repository.getCharacters(page)
  }
}