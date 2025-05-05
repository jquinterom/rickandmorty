package co.mrcomondev.pro.rickandmorty.domain.usecases

import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.models.Result
import co.mrcomondev.pro.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

/**
 * Created by gesoft
 */
class GetCharacterUseCase @Inject constructor(
  private val repository: CharacterRepository
) {
  suspend operator fun invoke(characterId: Int): Result<CharacterDomain> {
    return repository.getCharacter(characterId)
  }
}