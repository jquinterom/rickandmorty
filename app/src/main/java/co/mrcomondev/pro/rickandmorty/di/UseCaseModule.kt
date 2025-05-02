package co.mrcomondev.pro.rickandmorty.di

import co.mrcomondev.pro.rickandmorty.domain.repository.CharacterRepository
import co.mrcomondev.pro.rickandmorty.domain.usecases.GetCharactersStreamUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by gesoft
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

  @Provides
  @Singleton
  fun provideCharacterStreamUseCase(characterRepository: CharacterRepository): GetCharactersStreamUseCase {
    return GetCharactersStreamUseCase(characterRepository)
  }
}