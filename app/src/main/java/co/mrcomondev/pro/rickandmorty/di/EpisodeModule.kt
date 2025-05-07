package co.mrcomondev.pro.rickandmorty.di

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.EpisodeDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.EpisodeApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.EpisodeMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.repository.EpisodeRepositoryImpl
import co.mrcomondev.pro.rickandmorty.domain.models.Episode
import co.mrcomondev.pro.rickandmorty.domain.repository.EpisodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by gesoft
 */
@InstallIn(SingletonComponent::class)
@Module
object EpisodeModule {

  @Provides
  @Singleton
  fun provideEpisodeApiService(retrofit: Retrofit): EpisodeApiService {
    return retrofit.create(EpisodeApiService::class.java)
  }

  @Provides
  @Singleton
  fun provideEpisodeMapper(): EpisodeMapper {
    return EpisodeMapper()
  }

  @Provides
  @Singleton
  fun provideEpisodeApiResponseMapper(locationMapper: EpisodeMapper): ApiResponseMapper<EpisodeDto, Episode> {
    return ApiResponseMapper(locationMapper)
  }

  @Provides
  @Singleton
  fun provideEpisodeRepository(
    episodeApiService: EpisodeApiService,
    apiResponseMapper: ApiResponseMapper<EpisodeDto, Episode>
  ): EpisodeRepository {
    return EpisodeRepositoryImpl(episodeApiService, apiResponseMapper)
  }
}