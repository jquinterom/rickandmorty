package co.mrcomondev.pro.rickandmorty.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.CharacterDto
import co.mrcomondev.pro.rickandmorty.dataaccess.local.CharactersDataStore
import co.mrcomondev.pro.rickandmorty.dataaccess.local.dataStore
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.CharacterApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.CharacterMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.EpisodeMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.OriginMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.repository.CharacterRepositoryImpl
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by gesoft
 */
@InstallIn(SingletonComponent::class)
@Module
object CharacterModule {

  @Provides
  @Singleton
  fun provideCharacterApiService(retrofit: Retrofit): CharacterApiService {
    return retrofit.create(CharacterApiService::class.java)
  }

  @Provides
  @Singleton
  fun provideCharacterMapper(
    originMapper: OriginMapper,
  ): CharacterMapper {
    return CharacterMapper(originMapper)
  }

  @Provides
  @Singleton
  fun provideCharacterApiResponseMapper(characterMapper: CharacterMapper): ApiResponseMapper<CharacterDto, CharacterDomain> {
    return ApiResponseMapper(characterMapper)
  }

  @Provides
  @Singleton
  fun provideCharacterRepository(
    characterApiService: CharacterApiService,
    provideCharacterMapper: CharacterMapper,
    provideApiResponseMapper: ApiResponseMapper<CharacterDto, CharacterDomain>,
    provideEpisodeMapper: EpisodeMapper
  ): CharacterRepository {
    return CharacterRepositoryImpl(
      characterApiService,
      provideCharacterMapper,
      provideApiResponseMapper,
      provideEpisodeMapper
    )
  }

  @Provides
  @Singleton
  fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
    return context.dataStore
  }

  @Provides
  @Singleton
  fun provideCharactersDataStore(dataStore: DataStore<Preferences>): CharactersDataStore {
    return CharactersDataStore(dataStore)
  }
}