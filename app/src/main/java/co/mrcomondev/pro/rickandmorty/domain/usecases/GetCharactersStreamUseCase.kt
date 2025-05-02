package co.mrcomondev.pro.rickandmorty.domain.usecases

import androidx.paging.PagingData
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by gesoft
 */
class GetCharactersStreamUseCase @Inject constructor(
  private val repository: CharacterRepository
) {
  operator fun invoke(): Flow<PagingData<CharacterDomain>> {
    return repository.getCharactersStream()
  }
}